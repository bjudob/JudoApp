package com.example.botond.judoapp_4.activities;

import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.botond.judoapp_4.R;

public class StopwatchActivity extends BaseActivity {

    private Chronometer chronometer;
    private boolean running=false;
    private long pauseOffset=0;
    Button buttonStart, buttonPause, buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chronometer=(Chronometer) findViewById(R.id.chronometer);
        buttonStart=(Button) findViewById(R.id.buttonStart);
        buttonPause=(Button) findViewById(R.id.buttonPause);
        buttonReset=(Button) findViewById(R.id.buttonReset);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStartClick(view);
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPauseClick(view);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonResetClick(view);
            }
        });

    }

    private void buttonStartClick(View view){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            running=true;
        }
    }

    private void buttonPauseClick(View view){
        if(running){
            chronometer.stop();
            pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
            running=false;
        }
    }

    private void buttonResetClick(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset=0;
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_stopwatch;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_scoreboard;
    }
}
