package com.example.botond.judoapp_4.activities.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.BaseActivity;
import com.example.botond.judoapp_4.activities.auth.LogInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile2Activity extends BaseActivity implements ProfileMVP.view{

    private static final int CHOOSE_IMAGE = 101;

    private ProfileMVP.presenter presenter;

    @BindView(R.id.textViewUsername)
    TextView textViewDisplayName;
    @BindView(R.id.textViewVerifiedEmail)
    TextView textViewVerifiedEmail;

    @BindView(R.id.imageViewProfilePic)
    ImageView imageViewProfilePic;
    @BindView(R.id.imageViewBelt)
    ImageView imageViewBelt;
    @BindView(R.id.progressbarProfileImage)
    ProgressBar progressBar;

    private Uri uriProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile2);
        ButterKnife.bind(this);

        presenter=ProfilePresenter.getInstance(this,this);

        presenter.loadUserInfo();
        presenter.loadBelt();

        FirebaseUser user=presenter.getCurrentUser();

        if(user==null){
            finish();
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        }

        textViewVerifiedEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Profile2Activity.this, "Verification email sent!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        imageViewProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.profileImageClick();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uriProfileImage=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
                //imageViewProfilePic.setImageBitmap(bitmap);

                presenter.uploadImageToFirebaseStorage(uriProfileImage);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_profile2;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_profile;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBeltImage(int img) {
        Picasso.with(this).load(img).into(imageViewBelt);

    }

    @Override
    public void showImageChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"),CHOOSE_IMAGE);
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
        Glide.get(this).setMemoryCategory(MemoryCategory.LOW);
        Glide.with(this)
                .load(photoUrl)
                .into(imageViewProfilePic);
    }

    @Override
    public void setUsername(String username) {
        textViewDisplayName.setText(username);
    }

    @Override
    public void setEmail(String text) {
        textViewVerifiedEmail.setText(text);
        textViewVerifiedEmail.setVisibility(View.VISIBLE);
    }

}
