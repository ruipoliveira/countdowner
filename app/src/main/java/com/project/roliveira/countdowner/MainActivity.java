package com.project.roliveira.countdowner;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String FORMAT = "%02d:%02d";
    final TextView mText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mText = (TextView) findViewById(R.id.text_view_id);

        String time = "01:02";

        long min = Integer.parseInt(time.substring(0, 2));
        long sec = Integer.parseInt(time.substring(3));

        long t = (min * 60L) + sec;

        long result = TimeUnit.SECONDS.toMillis(t);

        new CountDownTimer(result, 1000) {

            public void onTick(long millisUntilFinished) {
                mText.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                mText.setText("done!");
            }
        }.start();

    }

    private void getRGBColor(int n) {
        int R = (255 * n) / 100;
        int G = (255 * (100 - n)) / 100;
        int B = 0;
    }
}
