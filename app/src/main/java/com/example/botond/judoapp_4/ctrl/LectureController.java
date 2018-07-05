package com.example.botond.judoapp_4.ctrl;

import android.widget.ArrayAdapter;

import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.domain.LectureCategory;
import com.example.botond.judoapp_4.repo.LectureRepository;

import java.util.List;

public class LectureController {

    private LectureRepository repo;

    public LectureController(){
        repo=new LectureRepository();
    }

    public LectureCategory getByName(String id) {
        return repo.getByName(id);
    }

    public void addLecture(Lecture lecture){
        repo.addLecture(lecture);
    }

    public List<LectureCategory> getLectureCategories(){
        return repo.getLectureCategories();
    }

    public Lecture getLecture(String id){return repo.getLecture(id);}

}
