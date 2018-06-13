package com.example.botond.judoapp_4.activities.profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile2Activity extends BaseActivity implements ProfileMVP.view{

    private static final int CHOOSE_IMAGE = 101;

    private ProfileMVP.presenter presenter;

    @BindView(R.id.textViewUsername)
    TextView textViewDisplayName;

    @BindView(R.id.imageViewProfilePic)
    ImageView imageViewProfilePic;

    @BindView(R.id.imageViewBelt)
    ImageView imageViewBelt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile2);
        ButterKnife.bind(this);

        presenter=ProfilePresenter.getInstance(this,this);

        presenter.loadUserInfo();
        presenter.loadBelt();

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
    public Context getContext() {
        return this.getBaseContext();
    }

    @Override
    public void setProgressBarVisibility(int visibility) {

    }

    @Override
    public void loadProfilePicture(String photoUrl) {

    }

    @Override
    public void setUsername(String username) {
        textViewDisplayName.setText(username);
    }

    @Override
    public void setEmail(String username) {

    }
}
