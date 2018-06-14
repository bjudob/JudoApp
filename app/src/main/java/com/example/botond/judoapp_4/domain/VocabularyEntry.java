package com.example.botond.judoapp_4.domain;

public class VocabularyEntry {
    private String jpn;
    private String eng;

    public VocabularyEntry() {
    }

    public VocabularyEntry(String jpn, String eng) {
        this.jpn = jpn;
        this.eng = eng;
    }

    public String getJpn() {
        return jpn;
    }

    public void setJpn(String jpn) {
        this.jpn = jpn;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }
}
