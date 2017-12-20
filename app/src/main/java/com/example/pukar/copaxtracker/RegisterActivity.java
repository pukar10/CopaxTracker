package com.example.pukar.copaxtracker;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pukar.copaxtracker.MainActivity;
import com.example.pukar.copaxtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtPass, txtPassConfirm;
    private Button btnReg;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        txtEmail = (EditText) findViewById(R.id.editText3);
        txtPass = (EditText) findViewById(R.id.editText4);
        txtPassConfirm = (EditText) findViewById(R.id.editText5);
        btnReg = (Button) findViewById(R.id.button4);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);

        progressBar.setVisibility(View.INVISIBLE);

        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmail.setText("");
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();
                String passConfirm = txtPassConfirm.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter Email Address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(passConfirm)){
                    Toast.makeText(getApplicationContext(), "Confirm Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pass.equals(passConfirm)){
                    Toast.makeText(getApplicationContext(), "Passwords do not Match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //PUT ALL THIS IN A TRY/CATCH BLOCK FOR NOW CANT REGISTER UNLESS EMAIL AND PASS STRONG ENOUGH!
                Task<AuthResult> authResultTask = auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "User Created!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Log.e("TAG", task.getException().getMessage());
                                }
                            }
                        });

            }
        });

    }
}
