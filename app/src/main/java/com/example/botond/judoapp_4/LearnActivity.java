package com.example.botond.judoapp_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.botond.judoapp_4.auth.SignUpActivity;
import com.example.botond.judoapp_4.ctrl.LectureController;
import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.repo.LectureRepository;

import java.util.List;

public class LearnActivity extends BaseActivity {

    private Button basicsButton, techniquesButton, kataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_learn);

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
    int getContentViewId() {
        return R.layout.activity_learn;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_learn;
    }
}
