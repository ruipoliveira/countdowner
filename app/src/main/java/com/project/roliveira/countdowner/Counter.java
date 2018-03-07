package com.project.roliveira.countdowner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Counter extends AppCompatActivity {

    private static final String FORMAT = "%02d:%02d";
    final TextView mText = null;
    private static long finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        getSupportActionBar().hide();

        // screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final TextView mText = (TextView) findViewById(R.id.text_view_id);

        String minutes = getIntent().getStringExtra("MINUTES_VALUE");
        String seconds = getIntent().getStringExtra("SECONDS_VALUE");

        long min = Long.valueOf(minutes).longValue();
        long sec = Long.valueOf(seconds).longValue();

        long t = (min * 60L) + sec;

        finalResult = TimeUnit.SECONDS.toMillis(t);

        new CountDownTimer(finalResult, 1000) {

            public void onTick(long millisUntilFinished) {
                mText.setText(String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                int color = (int) -((100 * millisUntilFinished) / finalResult) + 100;
                getWindow().getDecorView().setBackgroundColor(getRGBColor(color));

                Log.d("COLOR", " value ->" + color);
            }

            public void onFinish() {
                mText.setText("done!");
                Intent intent = new Intent(Counter.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();

    }

    /*
    * Get color 0 -> 100
    * red to green
    * */
    private int getRGBColor(int n) {
        int R = (255 * n) / 100;
        int G = (255 * (100 - n)) / 100;
        int B = 0;
        return Color.rgb(R, G, B);
    }
}
