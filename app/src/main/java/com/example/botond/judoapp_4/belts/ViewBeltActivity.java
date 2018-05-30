package com.example.botond.judoapp_4.belts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.manager.ResourceManager;
import com.example.botond.judoapp_4.repo.BeltRepository;

public class ViewBeltActivity extends AppCompatActivity {

    private Belt belt;
    private BeltController beltController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_belt);

        Intent intent = getIntent();
        String beltName = intent.getStringExtra("beltName");

        beltController= ResourceManager.getBeltController();

        Belt belt=beltController.getByName(beltName);

        if(belt==null){
            //error, data not found, go back
            // TODO: 30.05.2018
        }
        else{

        }
    }
}
