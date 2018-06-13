package com.example.botond.judoapp_4.activities.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.BaseActivity;

public class Profile2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_profile2);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_profile2;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_profile;
    }
}
