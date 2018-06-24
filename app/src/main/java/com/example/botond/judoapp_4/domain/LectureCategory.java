package com.example.botond.judoapp_4.domain;

import java.util.ArrayList;
import java.util.List;

public class LectureCategory {
    private String name;
    private List<Lecture> lectures;

    public LectureCategory(String name) {
        this.name=name;
        lectures=new ArrayList<>();
    }

    public void add(Lecture l){
        lectures.add(l);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }
}
