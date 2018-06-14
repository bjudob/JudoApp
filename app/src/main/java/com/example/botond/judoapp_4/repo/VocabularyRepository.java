package com.example.botond.judoapp_4.repo;

import com.example.botond.judoapp_4.domain.Vocabulary;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class VocabularyRepository {
    DatabaseReference databaseReference;
    List<Vocabulary> vocabularies;

    public VocabularyRepository(){
        vocabularies=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("dictionary");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name = dataSnapshot.getKey();

                Vocabulary vocabulary=new Vocabulary(name);

                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    String jpn = (String) ds.child("jpn").getValue();
                    String eng = (String) ds.child("eng").getValue();

                    if(jpn!=null && eng!=null){
                        vocabulary.put(jpn,eng);
                    }
                }

                vocabularies.add(vocabulary);

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


    public Vocabulary getByName(String name){
        for(Vocabulary v:vocabularies){
            if(v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }

    public List<Vocabulary> getVocabularies(){
        return vocabularies;
    }
}
