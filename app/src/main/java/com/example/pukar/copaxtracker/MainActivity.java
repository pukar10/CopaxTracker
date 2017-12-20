package com.example.pukar.copaxtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pukar.copaxtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnReg;
    private EditText txtEmail, txtPass;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        updateUI(auth);

        btnLogin = (Button) findViewById(R.id.button2);
        btnReg = (Button) findViewById(R.id.button3);
        txtEmail = (EditText) findViewById(R.id.editText);
        txtPass = (EditText) findViewById(R.id.editText2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        progressBar.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });

        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmail.setText("");
                txtEmail.setGravity(Gravity.START);
            }
        });

        txtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                txtPass.setText("");
                txtPass.setGravity(Gravity.START);
            }
        });

    }

    private void login(){
        String email = txtEmail.getText().toString();
        String pass = txtPass.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                        } else {
                            MainActivity.this.startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            finish();
                        }
                    }
                });
    }

    private void updateUI(FirebaseAuth user){
        if(user != null){
            //MainActivity.this.startActivity(new Intent(MainActivity.this, HomeActivity.class));
            //finish();
        }else{
            return;
        }
    }
}
