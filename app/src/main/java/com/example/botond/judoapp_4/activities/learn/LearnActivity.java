package com.example.botond.judoapp_4.activities.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.BaseActivity;
import com.example.botond.judoapp_4.activities.learn.belts.BeltsActivity;
import com.example.botond.judoapp_4.manager.ResourceManager;

public class LearnActivity extends BaseActivity {

    private Button basicsButton, techniquesButton, kataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_learn);

        ResourceManager.init();

        techniquesButton=(Button) findViewById(R.id.buttonTechniques);

        techniquesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                techniquesButtonClick(view);
            }
        });

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
}
