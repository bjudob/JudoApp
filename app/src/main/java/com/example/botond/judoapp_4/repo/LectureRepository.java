package com.example.botond.judoapp_4.repo;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.botond.judoapp_4.domain.Lecture;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LectureRepository {

    DatabaseReference databaseReference;
    List<Lecture> lectures;
    ArrayAdapter<Lecture> adapter;

    public LectureRepository(){
        lectures=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("lectures");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String title = (String) dataSnapshot.child("title").getValue();
                String author = (String) dataSnapshot.child("author").getValue();
                String text = (String) dataSnapshot.child("text").getValue();
                String photo = (String) dataSnapshot.child("photo").getValue();

                Lecture l = new Lecture(id, title, author, text, photo);

                lectures.add(l);

                if(adapter!=null){
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public List<Lecture> getLectures(){
        return lectures;
    }

    public void addLecture(Lecture lecture){
        HashMap<String, String> dataMap=new HashMap<>();

        dataMap.put("title", lecture.getTitle());
        dataMap.put("text", lecture.getText());
        dataMap.put("author", lecture.getAuthor());
        dataMap.put("photo", lecture.getPhoto());

        databaseReference.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //gut
                }
                else{
                    //not gut
                }

            }
        });
    }

    public ArrayAdapter<Lecture> getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter<Lecture> adapter) {
        this.adapter = adapter;
    }
}
