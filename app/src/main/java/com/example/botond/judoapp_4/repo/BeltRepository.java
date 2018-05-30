package com.example.botond.judoapp_4.repo;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.domain.Throw;
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

public class BeltRepository {

    DatabaseReference databaseReference;
    List<Belt> belts;

    public BeltRepository(){
        belts=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("belts");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String name = (String) dataSnapshot.child("name").getValue();
                String name_jpn = (String) dataSnapshot.child("name_jpn").getValue();

               DataSnapshot belt_throws=dataSnapshot.child("throws");

                Belt b = new Belt(id, name, name_jpn);

                for (DataSnapshot ds:belt_throws.getChildren()) {
                    String throw_id=ds.getKey();
                    String throw_name = (String) ds.child("name").getValue();
                    String throw_description = (String) ds.child("description").getValue();
                    String throw_img = (String) ds.child("img").getValue();

                    Throw t=new Throw(throw_id, throw_name, throw_description, throw_img);

                    b.addThrow(t);
                }

                belts.add(b);

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

    public Belt getById(String id){
        for(Belt b:belts){
            if(b.getId().equals(id)){
                return b;
            }
        }
        return null;
    }

    public List<Belt> getBelts(){
        return belts;
    }

    public void addBelt(Belt belt){
        HashMap<String, String> dataMap=new HashMap<>();

        dataMap.put("name", belt.getName());


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
