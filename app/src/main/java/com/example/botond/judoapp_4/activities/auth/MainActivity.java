package com.example.botond.judoapp_4.activities.auth;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.botond.judoapp_4.activities.learn.LearnActivity;
import com.example.botond.judoapp_4.activities.profile.ProfileActivity;
import com.example.botond.judoapp_4.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private FirebaseAuth mAuth;
    private VideoView videoView;

    @BindView(R.id.buttonLoginMain)
    Button loginButton;

    @BindView(R.id.buttonSignup)
    Button signupButton;

    @BindView(R.id.buttonFacebookLogin)
    Button facebookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this.getApplicationContext();

        mAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.buttonLoginMain);
        signupButton = findViewById(R.id.buttonSignup);
        facebookButton = findViewById(R.id.buttonFacebookLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInButtonClick(view);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpButtonClick(view);
            }
        });
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookButtonClick(view);
            }
        });

        setUpBackgroundVideo();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setUpBackgroundVideo();
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(mAuth.getCurrentUser()!=null){
            finish();
            Intent intent = new Intent(context, ProfileActivity.class);
            startActivity(intent);
        }
    }

    private void setUpBackgroundVideo(){
        videoView=(VideoView)findViewById(R.id.videoView2);
        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.background_ippon);

        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }

    public void logInButtonClick(View view){

        Intent intent = new Intent(context, LogInActivity.class);

        startActivity(intent);

    }

    public void signUpButtonClick(View view){

        Intent intent = new Intent(context, SignUpActivity.class);

        startActivity(intent);

    }

    public void facebookButtonClick(View view){

        //Intent intent = new Intent(context, LearnActivity.class);

        //startActivity(intent);

    }
}
