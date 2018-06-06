package com.example.botond.judoapp_4.activities.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.BaseActivity;
import com.example.botond.judoapp_4.activities.auth.LogInActivity;
import com.example.botond.judoapp_4.manager.ResourceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ProfileActivity extends BaseActivity implements ProfileMVP.view{

    private static final int CHOOSE_IMAGE = 101;

    private ProfileMVP.presenter presenter;

    private TextView textViewEmailVerified, textViewUsername;
    private ImageView imageView, imageViewBelt;
    private ProgressBar progressBar;

    private Uri uriProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);

        presenter=new ProfilePresenter(this,this);

        imageView=(ImageView) findViewById(R.id.imageViewCamera);
        imageViewBelt=(ImageView) findViewById(R.id.imageViewBelt);
        textViewUsername =(TextView) findViewById(R.id.textViewUsername);
        progressBar=(ProgressBar) findViewById(R.id.progressbarProfileImage);
        textViewEmailVerified=(TextView) findViewById(R.id.textViewVerifiedEmail);

        presenter.loadUserInfo();
        presenter.loadBelt();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });

        FirebaseUser user=presenter.getCurrentUser();

        if(user==null){
            finish();
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        }

        textViewEmailVerified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ProfileActivity.this, "Verification email sent!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadUserInfo();
        presenter.loadBelt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.reset();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_profile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uriProfileImage=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
                imageView.setImageBitmap(bitmap);

                presenter.uploadImageToFirebaseStorage(uriProfileImage);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void showImageChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"),CHOOSE_IMAGE);
    }


    @Override
    public void showToast(String text) {
        Toast.makeText(ProfileActivity.this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBeltImage(Drawable drawable) {
        imageViewBelt.setImageDrawable(drawable);
    }

    @Override
    public Context getContext() {
        return this.getBaseContext();
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void loadProfilePicture(String photoUrl) {
        Glide.with(this)
                .load(photoUrl)
                .into(imageView);
    }

    @Override
    public void setUsername(String username) {
        textViewUsername.setText(username);
    }

    @Override
    public void setEmail(String email) {
        textViewEmailVerified.setText(email);
        textViewEmailVerified.setVisibility(View.VISIBLE);
    }
}
