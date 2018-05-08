package com.example.botond.judoapp_4.ctrl;

import android.widget.ArrayAdapter;

import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.repo.LectureRepository;

import java.util.List;

public class LectureController {

    private LectureRepository repo;

    public LectureController(){
        repo=new LectureRepository();
    }

    public void addLecture(Lecture lecture){
        repo.addLecture(lecture);
    }

    public List<Lecture> getLectures(){
        return repo.getLectures();
    }

    public ArrayAdapter<Lecture> getAdapter() {
        return repo.getAdapter();
    }

    public void setAdapter(ArrayAdapter<Lecture> adapter) {
        repo.setAdapter(adapter);
    }
}
