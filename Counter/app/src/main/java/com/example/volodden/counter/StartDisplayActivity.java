package com.example.volodden.counter;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class StartDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("StAct", "Create Start");

        setContentView(R.layout.activity_start_display);

        Log.i("StAct", "Timer up");

        final StartDisplayActivity thisActivity = this;

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                Log.i("StAct", "Timer down");

                Intent intent = new Intent(thisActivity, CounterActivity.class);
                startActivity(intent);
                thisActivity.finish();
            }
        }.start();

        Log.i("StAct", "Call Counter");
    }

    @Override
    protected void onDestroy() {
        Log.i("StAct", "Start destroy");
        super.onDestroy();
    }
}
