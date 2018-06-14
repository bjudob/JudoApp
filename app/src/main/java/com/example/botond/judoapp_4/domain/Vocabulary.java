package com.example.botond.judoapp_4.domain;

import java.util.HashMap;

public class Vocabulary {
    private HashMap<String,String> hashMapVocabulary;

    public Vocabulary() {
        hashMapVocabulary=new HashMap<>();
    }

    public void put(String jpn, String eng){
        hashMapVocabulary.put(jpn, eng);
    }
}
