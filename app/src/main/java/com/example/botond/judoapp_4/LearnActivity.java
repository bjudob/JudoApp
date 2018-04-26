package com.example.botond.judoapp_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LearnActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_learn);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_learn;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_learn;
    }
}
