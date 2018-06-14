package com.example.botond.judoapp_4.domain;

import java.util.HashMap;

public class Vocabulary {
    private String name;
    private HashMap<String,String> hashMapVocabulary;

    public Vocabulary(String name) {
        this.name=name;
        hashMapVocabulary=new HashMap<>();
    }

    public void put(String jpn, String eng){
        hashMapVocabulary.put(jpn, eng);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
