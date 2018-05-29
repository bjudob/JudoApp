package com.example.botond.judoapp_4.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Belt {
    private String id;
    private String name;
    private List<Throw> throwList;

    public Belt() {
        throwList=new ArrayList<>();
    }

    public Belt(String name) {
        this.name = name;
        throwList=new ArrayList<>();
    }

    public Belt(String id, String name) {
        this.id = id;
        this.name = name;
        throwList=new ArrayList<>();
    }

    public void addThrow(Throw t){
        throwList.add(t);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Throw> getThrowList() {
        return throwList;
    }

    public void setThrowList(List<Throw> throwList) {
        this.throwList = throwList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Belt belt = (Belt) o;
        return Objects.equals(id, belt.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
