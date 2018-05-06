package com.example.botond.judoapp_4.domain;

public class Lecture {
    private String id;
    private String title;
    private String text;
    private String photo;
    private String authorId;

    //no args constructor
    public Lecture() {
    }

    //all args constructor
    public Lecture(String id, String title, String text, String photo, String authorId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.photo = photo;
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
