package com.example.botond.judoapp_4.activities.learn.belts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;
import com.felipecsl.gifimageview.library.GifImageView;

public class ViewThrowActivity extends AppCompatActivity {

    private Belt belt;
    private BeltController beltController;
    private Throw currentThrow;
    private GifImageView gifImageView;
    private Button buttonPrev, buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_throw);

        Intent intent = getIntent();
        String beltName = intent.getStringExtra("beltName");
        int throwIndex = intent.getIntExtra("throwIndex",0);

        beltController= ResourceManager.getBeltController();

        belt=beltController.getByName(beltName);

        if(belt==null){
            //error, data not found, go back
            // TODO: 30.05.2018
        }
        else{
            currentThrow=belt.getThrowList().get(throwIndex);
        }


    }
}
