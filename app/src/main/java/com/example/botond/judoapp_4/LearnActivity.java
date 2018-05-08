package com.example.botond.judoapp_4;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.botond.judoapp_4.ctrl.LectureController;
import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.repo.LectureRepository;

import java.util.List;

public class LearnActivity extends BaseActivity {

    private List<Lecture> lectures;
    private ArrayAdapter<Lecture> adapter;

    private ListView listView;
    private LectureController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_learn);

        listView=(ListView) findViewById(R.id.listViewLectures);

        ctrl=new LectureController();

        lectures=ctrl.getLectures();

        adapter = new ArrayAdapter<Lecture>(this,
                R.layout.listview_elem, lectures);

        ctrl.setAdapter(adapter);
        listView.setAdapter(adapter);

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
