package com.example.botond.judoapp_4.repo;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.botond.judoapp_4.domain.Belt;
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

public class BeltRepository {

    DatabaseReference databaseReference;
    List<Belt> belts;
    ArrayAdapter<Belt> adapter;

    public BeltRepository(){
        belts=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("belts");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String name = (String) dataSnapshot.child("name").getValue();

                Belt b = new Belt(id, name);

                belts.add(b);

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

    public ArrayAdapter<Belt> getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter<Belt> adapter) {
        this.adapter = adapter;
    }
}
