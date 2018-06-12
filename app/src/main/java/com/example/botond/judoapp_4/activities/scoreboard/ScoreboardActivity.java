package com.example.botond.judoapp_4.activities.scoreboard;

import android.content.Context;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.BaseActivity;
import com.example.botond.judoapp_4.domain.scores.PlayerScore;
import com.example.botond.judoapp_4.domain.scores.PlayerScore2018;
import com.example.botond.judoapp_4.domain.scores.ScoreDisplay;

public class ScoreboardActivity extends BaseActivity implements ScoreboardMVP.view{
    private Context context=this;

    private ScoreboardMVP.presenter presenter;

    private Chronometer chronometer;
    private Chronometer chronometerOsaekomi;

    private ScoreDisplay scoreDisplayWhite, scoreDisplayBlue;

    private Button buttonOsaekomi, buttonOsaekomiWhite, buttonOsaekomiBlue;

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

        presenter=new ScoreboardPresenter(this,this);

        setListeners();


        //chronometer.setCountDown(true);
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
                view.addWazari(1, playerWhite, scoreDisplayWhite);
            }
        });

        scoreDisplayWhite.getWazari().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.addWazari(-1, playerWhite, scoreDisplayWhite);
                return true;
            }
        });

        scoreDisplayBlue.getWazari().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.addWazari(1, playerBlue, scoreDisplayBlue);
            }
        });

        scoreDisplayBlue.getWazari().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.addWazari(-1, playerBlue, scoreDisplayBlue);
                return true;
            }
        });
    }

    //setting listeners for the IPPON textViews
    private void setListenersIppon(){
        scoreDisplayWhite.getIppon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.addIppon(1, playerWhite, scoreDisplayWhite);
            }
        });

        scoreDisplayWhite.getIppon().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.addIppon(-1, playerWhite, scoreDisplayWhite);
                return true;
            }
        });

        scoreDisplayBlue.getIppon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.addIppon(1, playerBlue, scoreDisplayBlue);
            }
        });

        scoreDisplayBlue.getIppon().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.addIppon(-1, playerBlue, scoreDisplayBlue);
                return true;
            }
        });

    }

    //setting listeners for the SHIDO imageViews
    private void setListenersShido(){
        scoreDisplayWhite.getShido().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.addShido(1, playerWhite, scoreDisplayWhite);
            }
        });

        scoreDisplayWhite.getShido().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.addShido(-1, playerWhite, scoreDisplayWhite);
                return true;
            }
        });

        scoreDisplayBlue.getShido().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.addShido(1, playerBlue, scoreDisplayBlue);
            }
        });

        scoreDisplayBlue.getShido().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.addShido(-1, playerBlue, scoreDisplayBlue);
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

    @Override
    public void setShidoImage(PlayerScore player, ScoreDisplay scoreDisplay){
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
    protected int getContentViewId() {
        return R.layout.activity_scoreboard;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_scoreboard;
    }

}


