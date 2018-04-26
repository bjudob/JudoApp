package com.example.botond.judoapp_4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    VideoView videoView;
    Button loginButton, signupButton, facebookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            Intent intent = new Intent(this, ProfileActivity.class);
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

        Intent intent = new Intent(this, LogInActivity.class);

        startActivity(intent);

    }

    public void signUpButtonClick(View view){

        Intent intent = new Intent(this, SignUpActivity.class);

        startActivity(intent);

    }

    public void facebookButtonClick(View view){

        Intent intent = new Intent(this, LearnActivity.class);

        startActivity(intent);

    }
}
