package com.example.pukar.copaxtracker;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TrackerActivity extends AppCompatActivity {

    private Button btnAdd;
    private FloatingActionButton btnStart;
    private Spinner spinnerSites;
    private TextView txtCountDown, txtSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        btnStart = (FloatingActionButton) findViewById(R.id.button7);
        btnAdd = (Button) findViewById(R.id.button8);
        spinnerSites = (Spinner) findViewById(R.id.spinner);
        txtSite = (TextView) findViewById(R.id.textView4);

        btnAdd.setVisibility(View.INVISIBLE);
        spinnerSites.setVisibility(View.INVISIBLE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(TrackerActivity.this, TimerBroadcastService.class));
                btnAdd.setVisibility(View.INVISIBLE);
                spinnerSites.setVisibility(View.INVISIBLE);
                txtSite.setText(spinnerSites.getSelectedItem().toString());
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.setVisibility(View.VISIBLE);
                spinnerSites.setVisibility(View.VISIBLE);
            }
        });
    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(TimerBroadcastService.COUNTDOWN_BR));
    }

    private void updateGUI(Intent intent) {
        txtCountDown = (TextView) findViewById(R.id.textView11);
        long millisLeft = intent.getLongExtra("millisLeft", 0);
        long secondsLeft = millisLeft / 1000;
        long minutesLeft = secondsLeft / 60;
        long hoursLeft = minutesLeft / 60;
        long daysLeft = hoursLeft / 24;
        String time = daysLeft + "d : " + hoursLeft % 24 + " : " + minutesLeft % 60 + " : " + secondsLeft % 60;

        txtCountDown.setText(time);

    }

        @Override
    protected void onDestroy() {
        stopService(new Intent(this,TimerBroadcastService.class));
        super.onDestroy();
    }
}
