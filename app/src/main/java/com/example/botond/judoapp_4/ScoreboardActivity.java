package com.example.botond.judoapp_4;

import android.content.Context;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.botond.judoapp_4.scores.PlayerScore;
import com.example.botond.judoapp_4.scores.PlayerScore2018;
import com.example.botond.judoapp_4.scores.ScoreDisplay;

public class ScoreboardActivity extends BaseActivity {

    private static final int CONTEST_MINS = 4;
    private static final int CONTEST_TIME = CONTEST_MINS*60*1000;

    private Context context=this;

    private Chronometer chronometer;
    private boolean running=false;
    private long pauseOffset=0;

    private Chronometer chronometerOsaekomi;
    private boolean runningOsaekomi=false;
    private long pauseOffsetOsaekomi=0;

    private ScoreDisplay scoreDisplayWhite, scoreDisplayBlue;
    private PlayerScore playerWhite, playerBlue;

    private Button buttonOsaekomi, buttonOsaekomiWhite, buttonOsaekomiBlue;

    private PlayerScore playerOsaekomi;
    private ScoreDisplay scoreDisplayOsaekomi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scoreDisplayWhite=new ScoreDisplay();
        scoreDisplayBlue=new ScoreDisplay();

        chronometer=(Chronometer) findViewById(R.id.chronometer);
        chronometerOsaekomi=(Chronometer) findViewById(R.id.chronometerOsaekomi);
        buttonOsaekomi=(Button)findViewById(R.id.buttonOsaekomi);
        buttonOsaekomiWhite=(Button)findViewById(R.id.buttonOsaekomiWhite);
        buttonOsaekomiBlue=(Button)findViewById(R.id.buttonOsaekomiBlue);
        scoreDisplayWhite.setWazari((TextView) findViewById(R.id.textViewWazariWhite));
        scoreDisplayBlue.setWazari((TextView) findViewById(R.id.textViewWazariBlue));
        scoreDisplayWhite.setIppon((TextView) findViewById(R.id.textViewIpponWhite));
        scoreDisplayBlue.setIppon((TextView) findViewById(R.id.textViewIpponBlue));
        scoreDisplayWhite.setShido((ImageView) findViewById(R.id.imageViewShidoWhite));
        scoreDisplayBlue.setShido((ImageView) findViewById(R.id.imageViewShidoBlue));

        playerWhite=new PlayerScore2018();
        playerBlue=new PlayerScore2018();

        playerWhite.setOpponent(playerBlue);
        playerBlue.setOpponent(playerWhite);

        setListeners();

        //playerWhite.addShido(1);
        setShidoImage(playerWhite, scoreDisplayWhite);
        setShidoImage(playerBlue, scoreDisplayBlue);

        chronometer.setCountDown(true);
        chronometer.setBase(SystemClock.elapsedRealtime()+CONTEST_TIME);
        pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();

        chronometerOsaekomi.setBase(SystemClock.elapsedRealtime());
        pauseOffsetOsaekomi=SystemClock.elapsedRealtime()-chronometerOsaekomi.getBase();
    }

    //setting listeners for the UI
    private void setListeners(){

        setListenersButton();
        setListenersWazari();
        setListenersIppon();
        setListenersShido();
        setListenersChrono();
    }

    //setting listeners for the WAZARI textViews
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

    //setting listeners for the IPPON textViews
    private void setListenersIppon(){
        scoreDisplayWhite.getIppon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIppon(1, playerWhite, scoreDisplayWhite);
            }
        });

        scoreDisplayWhite.getIppon().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addIppon(-1, playerWhite, scoreDisplayWhite);
                return true;
            }
        });

        scoreDisplayBlue.getIppon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIppon(1, playerBlue, scoreDisplayBlue);
            }
        });

        scoreDisplayBlue.getIppon().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addIppon(-1, playerBlue, scoreDisplayBlue);
                return true;
            }
        });

    }

    //setting listeners for the SHIDO imageViews
    private void setListenersShido(){
        scoreDisplayWhite.getShido().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShido(1, playerWhite, scoreDisplayWhite);
            }
        });

        scoreDisplayWhite.getShido().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addShido(-1, playerWhite, scoreDisplayWhite);
                return true;
            }
        });

        scoreDisplayBlue.getShido().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShido(1, playerBlue, scoreDisplayBlue);
            }
        });

        scoreDisplayBlue.getShido().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addShido(-1, playerBlue, scoreDisplayBlue);
                return true;
            }
        });
    }

    //setting listeners for the OSAEKOMI buttons
    private void setListenersButton(){
        buttonOsaekomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOsaekomiBlue.setVisibility(View.VISIBLE);
                buttonOsaekomiWhite.setVisibility(View.VISIBLE);
                chronometerOsaekomi.setVisibility(View.VISIBLE);
                buttonOsaekomi.setVisibility(View.GONE);

                startChronoOsaekomi();
            }
        });

        buttonOsaekomiWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometerOsaekomi.setTextColor(getColor(R.color.colorWhite));

                playerOsaekomi=playerWhite;
                scoreDisplayOsaekomi=scoreDisplayWhite;

                buttonOsaekomiWhite.setVisibility(View.GONE);
                buttonOsaekomiBlue.setVisibility(View.GONE);
            }
        });

        buttonOsaekomiBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometerOsaekomi.setTextColor(getColor(R.color.colorBlue));

                playerOsaekomi=playerBlue;
                scoreDisplayOsaekomi=scoreDisplayBlue;

                buttonOsaekomiWhite.setVisibility(View.GONE);
                buttonOsaekomiBlue.setVisibility(View.GONE);
            }
        });
    }

    //setting listeners for the CHRONOMETERS
    private void setListenersChrono(){
        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running){
                    startChrono();
                }
                else{
                    pauseChrono();
                }
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if( chronometer.getText().toString().equalsIgnoreCase("00:00")) {
                    pauseChrono();
                }
            }
        });

        chronometerOsaekomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!runningOsaekomi){
                    startChronoOsaekomi();
                }
                else{
                    pauseChronoOsaekomi();
                }
            }
        });

        chronometerOsaekomi.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        if( chronometer.getText().toString().equalsIgnoreCase("00:20")) {
                            pauseChronoOsaekomi();
                        }
                    }
        });
    }

    private void addWazari(int nr, PlayerScore player, ScoreDisplay scoreDisplay){
        player.addWazari(nr);
        setScores();
    }

    private void addIppon(int nr, PlayerScore player, ScoreDisplay scoreDisplay){
        player.addIppon(nr);
        setScores();
    }

    private void addShido(int nr, PlayerScore player, ScoreDisplay scoreDisplay){
        player.addShido(nr);
        setScores();
    }

    private void setScores(){
        setScores(playerWhite, scoreDisplayWhite);
        setScores(playerBlue, scoreDisplayBlue);
    }

    private void setScores(PlayerScore player, ScoreDisplay scoreDisplay){
        String wazariText, ipponText;

        wazariText=player.getWazari().toString();

        if(player.getIppon()){
            ipponText="1";
        }
        else{
            ipponText="0";
        }

        scoreDisplay.getWazari().setText(wazariText);
        scoreDisplay.getIppon().setText(ipponText);
        setShidoImage(player,scoreDisplay);
    }

    private void setShidoImage(PlayerScore player, ScoreDisplay scoreDisplay){
        ImageView imageView=scoreDisplay.getShido();

        if(player.isHansokumake()){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.red_card));
        }
        else {
            switch (player.getShido()) {
                case 0:
                    imageView.setImageDrawable(getDrawable(R.drawable.yellow_card_0));
                    break;
                case 1:
                    imageView.setImageDrawable(getDrawable(R.drawable.yellow_card_1));
                    break;
                case 2:
                    imageView.setImageDrawable(getDrawable(R.drawable.yellow_card_2));
                    break;
                case 3:
                    imageView.setImageDrawable(getDrawable(R.drawable.yellow_card_3));
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

    private void startChrono(){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            chronometer.setTextColor(getResources().getColor(R.color.colorGreen));
            running=true;
        }
    }

    private void pauseChrono(){
        if(running){
            chronometer.stop();
            pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
            chronometer.setTextColor(getResources().getColor(R.color.colorRed));
            running=false;
        }
    }

    private void resetChrono(){
        chronometer.setBase(SystemClock.elapsedRealtime()+CONTEST_TIME);
        pauseOffset=0;
    }

    private void startChronoOsaekomi(){
        if(!runningOsaekomi){
            chronometerOsaekomi.setBase(SystemClock.elapsedRealtime()-pauseOffsetOsaekomi);
            chronometerOsaekomi.start();
            chronometerOsaekomi.setTextColor(getColor(R.color.colorGreen));
            runningOsaekomi=true;
        }
    }

    private void pauseChronoOsaekomi(){
        if(runningOsaekomi){
            chronometerOsaekomi.stop();
            pauseOffsetOsaekomi=SystemClock.elapsedRealtime()-chronometerOsaekomi.getBase();
            chronometerOsaekomi.setTextColor(getColor(R.color.colorRed));
            runningOsaekomi=false;

            addScoreOsaekomi(pauseOffsetOsaekomi);

            chronometerOsaekomi.setVisibility(View.GONE);
            buttonOsaekomi.setVisibility(View.VISIBLE);

            resetChronoOsaekomi();
        }
    }

    //adds score into scoreboard based on osaekomi time in milliseconds
    private void addScoreOsaekomi(long time){
        long seconds=time/1000;

        if(playerOsaekomi!=null && scoreDisplayOsaekomi!=null) {
            if (seconds < 10) {

            } else if (seconds < 20) {
                addWazari(1, playerOsaekomi, scoreDisplayOsaekomi);
            } else if (seconds >= 20) {
                addIppon(1, playerOsaekomi, scoreDisplayOsaekomi);
            }
            playerOsaekomi=null;
            scoreDisplayOsaekomi=null;
        }
        else{
            buttonOsaekomiWhite.setVisibility(View.GONE);
            buttonOsaekomiBlue.setVisibility(View.GONE);
        }

    }

    private void resetChronoOsaekomi(){
        chronometerOsaekomi.setBase(SystemClock.elapsedRealtime());
        pauseOffsetOsaekomi=0;
    }
}


