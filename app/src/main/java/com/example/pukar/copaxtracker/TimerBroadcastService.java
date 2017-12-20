package com.example.pukar.copaxtracker;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class TimerBroadcastService extends Service {

    private final String TAG = "TimerBroadcastService";
    private CountDownTimer cdt;
    public static final String COUNTDOWN_BR = "com.example.pukar.copaxtracker.countdown_br";
    private Intent myIntent = new Intent(COUNTDOWN_BR);

    public TimerBroadcastService() {
        super.onCreate();

        long startTime = System.currentTimeMillis();

        //259200000 = 3 days | use 3000 to test
        cdt = new CountDownTimer(259200000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                myIntent.putExtra("millisLeft", millisUntilFinished);
                sendBroadcast(myIntent);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer Finished");
            }
        };

        cdt.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        cdt.cancel();
        Log.i(TAG, "Timer/Service destroyed");
        super.onDestroy();
    }
}
