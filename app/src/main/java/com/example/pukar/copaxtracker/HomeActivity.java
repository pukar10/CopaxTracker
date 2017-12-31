package com.example.pukar.copaxtracker;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.pukar.copaxtracker.R;
import com.google.firebase.auth.FirebaseAuth;

import java.net.URI;

public class HomeActivity extends AppCompatActivity {

    private TextView txtTracker, txtTutorial, txtSettings, txtSignOut;
    private VideoView videoTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtTracker = (TextView) findViewById(R.id.textView8);
        txtTutorial = (TextView) findViewById(R.id.textView9);
        txtSettings = (TextView) findViewById(R.id.textView10);
        txtSignOut = (TextView) findViewById(R.id.textView13);
        videoTutorial = (VideoView) findViewById(R.id.videoView3);

        videoTutorial.setVisibility(View.INVISIBLE);

        txtTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, TrackerActivity.class));
            }
        });

        txtSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });

        txtTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
