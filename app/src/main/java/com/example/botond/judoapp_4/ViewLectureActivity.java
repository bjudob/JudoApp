package com.example.botond.judoapp_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.botond.judoapp_4.ctrl.LectureController;
import com.example.botond.judoapp_4.domain.Lecture;

public class ViewLectureActivity extends AppCompatActivity {

    private static LectureController ctrl;
    private TextView textViewTitle, textViewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lecture);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        textViewTitle=(TextView) findViewById(R.id.textViewTitle);
        textViewText=(TextView) findViewById(R.id.textViewText);

        ctrl=LearnActivity.getCtrl();

        Lecture lecture=ctrl.getById(id);
        loadUi(lecture);

    }

    private void loadUi(Lecture lecture){
        textViewTitle.setText(lecture.getTitle());
        textViewText.setText(lecture.getText());
    }
}
