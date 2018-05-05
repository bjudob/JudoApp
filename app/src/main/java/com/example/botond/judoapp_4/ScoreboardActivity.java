package com.example.botond.judoapp_4;

import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.botond.judoapp_4.scores.PlayerScore;
import com.example.botond.judoapp_4.scores.PlayerScore2018;
import com.example.botond.judoapp_4.scores.ScoreDisplay;

public class ScoreboardActivity extends BaseActivity {

    private static final int CONTEST_MINS = 4;
    private static final int CONTEST_TIME = CONTEST_MINS*60*1000;

    private Chronometer chronometer;
    private boolean running=false;
    private long pauseOffset=0;

    private ScoreDisplay scoreDisplayWhite, scoreDisplayBlue;
    private PlayerScore playerWhite, playerBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scoreDisplayWhite=new ScoreDisplay();
        scoreDisplayBlue=new ScoreDisplay();

        chronometer=(Chronometer) findViewById(R.id.chronometer);
        scoreDisplayWhite.setWazari((TextView) findViewById(R.id.textViewWazariWhite));
        scoreDisplayBlue.setWazari((TextView) findViewById(R.id.textViewWazariBlue));
        scoreDisplayWhite.setIppon((TextView) findViewById(R.id.textViewIpponWhite));
        scoreDisplayBlue.setIppon((TextView) findViewById(R.id.textViewIpponBlue));
        scoreDisplayWhite.setShido((ImageView) findViewById(R.id.imageViewShidoWhite));
        scoreDisplayBlue.setShido((ImageView) findViewById(R.id.imageViewShidoBlue));



        playerWhite=new PlayerScore2018();
        playerBlue=new PlayerScore2018();

        setListeners();

        //playerWhite.addShido(1);
        setShidoImage(playerWhite, scoreDisplayWhite);
        setShidoImage(playerBlue, scoreDisplayBlue);

        chronometer.setCountDown(true);
        chronometer.setBase(SystemClock.elapsedRealtime()+CONTEST_TIME);
        pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
    }

    private void setListeners(){

        setListenersWazari();
        setListenersIppon();
        setListenersShido();

        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running){
                    startChrono(view);
                }
                else{
                    pauseChrono(view);
                }
            }
        });


    }

    private void setListenersWazari(){
        scoreDisplayWhite.getWazari().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWazari(1, playerWhite, scoreDisplayWhite);
            }
        });

        scoreDisplayWhite.getWazari().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addWazari(-1, playerWhite, scoreDisplayWhite);
                return true;
            }
        });

        scoreDisplayBlue.getWazari().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWazari(1, playerBlue, scoreDisplayBlue);
            }
        });

        scoreDisplayBlue.getWazari().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addWazari(-1, playerBlue, scoreDisplayBlue);
                return true;
            }
        });
    }

    private void setListenersIppon(){
        scoreDisplayWhite.getIppon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIppon(1, playerBlue, scoreDisplayBlue);
            }
        });

        scoreDisplayBlue.getIppon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIppon(1, playerBlue, scoreDisplayBlue);
            }
        });

    }

    private void setListenersShido(){
        scoreDisplayWhite.getShido().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerWhite.addShido(1);
                setShidoImage(playerWhite, scoreDisplayWhite);
            }
        });

        scoreDisplayWhite.getShido().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                playerWhite.addShido(-1);
                setShidoImage(playerWhite, scoreDisplayWhite);
                return true;
            }
        });

        scoreDisplayBlue.getShido().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerBlue.addShido(1);
                setShidoImage(playerBlue, scoreDisplayBlue);
            }
        });

        scoreDisplayBlue.getShido().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                playerBlue.addShido(-1);
                setShidoImage(playerBlue, scoreDisplayBlue);
                return true;
            }
        });
    }


    private void addWazari(int nr, PlayerScore player, ScoreDisplay scoreDisplay){
        player.addWazari(nr);
        String wazariText=player.getWazari().toString();
        scoreDisplay.getWazari().setText(wazariText);
        //checkIppon(player, textView);
    }

    private void addIppon(int nr, PlayerScore player, ScoreDisplay scoreDisplay){
        player.addIppon(nr);

    }

    private void checkIppon(PlayerScore player, TextView textView){
        String ipponText;
        if(player.getIppon()){
            ipponText="1";
        }
        else{
            ipponText="0";
        }

        textView.setText(ipponText);
    }

    private void setShidoImage(PlayerScore player, ScoreDisplay scoreDisplay){
        ImageView imageView=scoreDisplay.getShido();

        if(player.isHansokumake()){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.red_card));
        }
        else {
            switch (player.getShido()) {
                case 0:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.yellow_card_0));
                    break;
                case 1:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.yellow_card_1));
                    break;
                case 2:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.yellow_card_2));
                    break;
                case 3:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.yellow_card_3));
                    break;
            }
        }
    }


    @Override
    int getContentViewId() {
        return R.layout.activity_scoreboard;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_scoreboard;
    }

    private void startChrono(View view){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            chronometer.setTextColor(getResources().getColor(R.color.colorGreen));
            running=true;
        }
    }

    private void pauseChrono(View view){
        if(running){
            chronometer.stop();
            pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
            chronometer.setTextColor(getResources().getColor(R.color.colorRed));
            running=false;
        }
    }

    private void resetChrono(View view){
        chronometer.setBase(SystemClock.elapsedRealtime()+CONTEST_TIME);
        pauseOffset=0;
    }
}
