package com.example.botond.judoapp_4;

import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.botond.judoapp_4.scores.PlayerScore;
import com.example.botond.judoapp_4.scores.PlayerScore2018;

public class ScoreboardActivity extends BaseActivity {

    private Chronometer chronometer;
    private boolean running=false;
    private long pauseOffset=0;

    private ImageView imageViewShidoWhite, imageViewShidoBlue;

    private TextView textViewWazariWhite, textViewWazariBlue;
    private PlayerScore playerWhite, playerBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chronometer=(Chronometer) findViewById(R.id.chronometer);
        textViewWazariWhite=(TextView) findViewById(R.id.textViewWazariWhite);
        textViewWazariBlue=(TextView) findViewById(R.id.textViewWazariBlue);
        imageViewShidoWhite=(ImageView) findViewById(R.id.imageViewShidoWhite);
        imageViewShidoBlue=(ImageView) findViewById(R.id.imageViewShidoBlue);

        playerWhite=new PlayerScore2018();
        playerBlue=new PlayerScore2018();

        setListeners();

        //playerWhite.addShido(1);
        setShidoImage(playerWhite, imageViewShidoWhite);
        setShidoImage(playerBlue, imageViewShidoBlue);

    }

    private void setListeners(){
        textViewWazariWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWazari(1, playerWhite, textViewWazariWhite);
            }
        });

        textViewWazariWhite.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addWazari(-1, playerWhite, textViewWazariWhite);
                return true;
            }
        });

        textViewWazariBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWazari(1, playerBlue, textViewWazariBlue);
            }
        });

        textViewWazariBlue.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addWazari(-1, playerBlue, textViewWazariBlue);
                return true;
            }
        });

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

        imageViewShidoWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerWhite.addShido(1);
                setShidoImage(playerWhite, imageViewShidoWhite);
            }
        });

        imageViewShidoWhite.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                playerWhite.addShido(-1);
                setShidoImage(playerWhite, imageViewShidoWhite);
                return true;
            }
        });

        imageViewShidoBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerBlue.addShido(1);
                setShidoImage(playerBlue, imageViewShidoBlue);
            }
        });

        imageViewShidoBlue.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                playerBlue.addShido(-1);
                setShidoImage(playerBlue, imageViewShidoBlue);
                return true;
            }
        });
    }



    private void addWazari(int nr, PlayerScore player, TextView textView){
        player.addWazari(nr);
        String wazariText=player.getWazari().toString();
        textView.setText(wazariText);
    }

    private void setShidoImage(PlayerScore player, ImageView imageView){
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
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset=0;
    }
}
