package com.example.botond.judoapp_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.botond.judoapp_4.ctrl.LectureController;

public class ViewLectureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lecture);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");


    }
}
