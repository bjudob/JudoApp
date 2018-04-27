package com.example.botond.judoapp_4;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class ScoreboardActivity extends BaseActivity {

    private Chronometer chronometer;
    private boolean running=false;
    private long pauseOffset=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chronometer=(Chronometer) findViewById(R.id.chronometer);


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
        return R.layout.activity_scoreboard;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_scoreboard;
    }
}
