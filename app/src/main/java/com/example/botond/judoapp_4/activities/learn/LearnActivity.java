package com.example.botond.judoapp_4.activities.learn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.BaseActivity;
import com.example.botond.judoapp_4.activities.learn.belts.BeltsActivity;
import com.example.botond.judoapp_4.manager.ResourceManager;

public class LearnActivity extends BaseActivity implements LearnMenuFragment.OnFragmentInteractionListener{

    //private Button basicsButton, techniquesButton, kataButton;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_learn);

        ResourceManager.init();

        frameLayout=(FrameLayout) findViewById(R.id.mainFrame);

        LearnMenuFragment imageSelectorFragment = new LearnMenuFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), imageSelectorFragment,
                LearnMenuFragment.class.getName());

        // Commit the transaction
        fragmentTransaction.commit();
    }

    private void techniquesButtonClick(View view) {
        Intent intent = new Intent(this, BeltsActivity.class);

        startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_learn;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_learn;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
