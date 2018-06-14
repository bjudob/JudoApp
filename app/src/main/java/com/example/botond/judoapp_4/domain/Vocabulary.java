package com.example.botond.judoapp_4.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vocabulary {
    private String name;
    private List<VocabularyEntry> vocabularyEntries;

    public Vocabulary(String name) {
        this.name=name;
        vocabularyEntries=new ArrayList<>();
    }

    public void add(VocabularyEntry ve){
        vocabularyEntries.add(ve);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VocabularyEntry> getVocabularyEntries() {
        return vocabularyEntries;
    }
}
