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

        listView. setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Lecture selectedLecture = lectures.get(position);

                Intent intent = new Intent(LearnActivity.this, ViewLectureActivity.class);

                intent.putExtra("id", selectedLecture.getId());
                startActivity(intent);
                finish();
            }
        });

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
