package com.project.roliveira.countdowner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private NumberPicker seconds, minutes;

    private TextView textview;

    private int seconds_int, minutes_int;

    /*
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_notifications:

                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        minutes = (NumberPicker) findViewById(R.id.numberPicker1);
        seconds = (NumberPicker) findViewById(R.id.numberPicker2);

        textview = (TextView) findViewById(R.id.message);


        minutes.setMinValue(0);
        minutes.setMaxValue(59);
        seconds.setMinValue(0);
        seconds.setMaxValue(59);

        minutes.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                minutes_int = newVal;
                textview.setText(String.format("%02d", minutes_int) + ":" + String.format("%02d", seconds_int));
            }
        });

        seconds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                seconds_int = newVal;
                textview.setText(String.format("%02d", minutes_int) + ":" + String.format("%02d", seconds_int));
            }
        });

        minutes.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });

        seconds.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });


    }

    public void sendMessage(View view) {

        Intent intent = new Intent(MainActivity.this, Counter.class);
        intent.putExtra("MINUTES_VALUE", String.valueOf(minutes_int));
        intent.putExtra("SECONDS_VALUE", String.valueOf(seconds_int));

        startActivity(intent);
    }


}
