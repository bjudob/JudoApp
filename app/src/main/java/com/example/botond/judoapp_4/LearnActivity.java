package com.example.botond.judoapp_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.botond.judoapp_4.ctrl.LectureController;
import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.repo.LectureRepository;

import java.util.List;

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
