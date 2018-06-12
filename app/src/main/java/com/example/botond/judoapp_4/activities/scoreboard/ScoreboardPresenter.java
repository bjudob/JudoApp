package com.example.botond.judoapp_4.activities.scoreboard;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.domain.scores.PlayerScore;
import com.example.botond.judoapp_4.domain.scores.PlayerScore2018;
import com.example.botond.judoapp_4.domain.scores.ScoreDisplay;

public class ScoreboardPresenter implements ScoreboardMVP.presenter{

    public enum Player{
        WHITE,
        BLUE
    }

    private static final int CONTEST_MINS = 0;
    private static final int CONTEST_TIME = CONTEST_MINS*60*1000;

    private ScoreboardMVP.view view;
    private Context context;

    private boolean running=false;
    private long pauseOffset=0;
    private boolean runningOsaekomi=false;
    private long pauseOffsetOsaekomi=0;

    private PlayerScore playerWhite, playerBlue;
    private PlayerScore playerOsaekomi;

    private ScoreDisplay scoreDisplayWhite, scoreDisplayBlue,scoreDisplayOsaekomi;

    public ScoreboardPresenter(ScoreboardMVP.view view, Context context){
        this.view=view;
        this.context=context;

        playerWhite=new PlayerScore2018();
        playerBlue=new PlayerScore2018();

        playerWhite.setOpponent(playerBlue);
        playerBlue.setOpponent(playerWhite);

        view.setShidoImage(playerWhite, scoreDisplayWhite);
        view.setShidoImage(playerBlue, scoreDisplayBlue);

    }

    @Override
    public void addWazari(int nr, PlayerScore player, ScoreDisplay scoreDisplay){
        player.addWazari(nr);
        setScores();
    }

    @Override
    public void addIppon(int nr, PlayerScore player, ScoreDisplay scoreDisplay){
        player.addIppon(nr);
        setScores();
    }

    @Override
    public void addShido(int nr, PlayerScore player, ScoreDisplay scoreDisplay){
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
        view.setShidoImage(player,scoreDisplay);
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
