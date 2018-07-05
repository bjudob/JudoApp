package com.example.botond.judoapp_4.repo;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.domain.LectureCategory;
import com.example.botond.judoapp_4.domain.Vocabulary;
import com.example.botond.judoapp_4.domain.VocabularyEntry;
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
    List<LectureCategory> lectureCategories;

    public LectureRepository(){
        lectureCategories=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("lectures");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String name = dataSnapshot.getKey();

                LectureCategory lectureCategory=new LectureCategory(name);

                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    String id= (String) ds.getKey();
                    String title = (String) ds.child("title").getValue();
                    String text = (String) ds.child("text").getValue();
                    String photo = (String) ds.child("photo").getValue();
                    String author = (String) ds.child("author").getValue();

                    lectureCategory.add(new Lecture(id, title,text,photo,author));

                }

                lectureCategories.add(lectureCategory);
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

    public LectureCategory getByName(String id){
        for(LectureCategory l:lectureCategories){
            if(l.getName().equals(id)){
                return l;
            }
        }
        return null;
    }

    public Lecture getLecture(String title){
        for(LectureCategory lc:lectureCategories){
            for(Lecture l: lc.getLectures()){
                if(l.getTitle().equals(title)){
                    return l;
                }
            }
        }
        return null;
    }

    public List<LectureCategory> getLectureCategories(){
        return lectureCategories;
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

}
