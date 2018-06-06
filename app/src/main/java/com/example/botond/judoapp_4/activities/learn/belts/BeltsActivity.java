package com.example.botond.judoapp_4.activities.learn.belts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.botond.judoapp_4.R;

public class BeltsActivity extends AppCompatActivity {

    private Button buttonWhite,buttonYellow,buttonOrange,buttonGreen,buttonBlue,buttonBrown,buttonBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belts);

        buttonWhite=(Button) findViewById(R.id.buttonWhite);
        buttonYellow=(Button) findViewById(R.id.buttonYellow);
        buttonOrange=(Button) findViewById(R.id.buttonOrange);
        buttonGreen=(Button) findViewById(R.id.buttonGreen);
        buttonBlue=(Button) findViewById(R.id.buttonBlue);
        buttonBrown=(Button) findViewById(R.id.buttonBrown);
        buttonBlack=(Button) findViewById(R.id.buttonBlack);

        buttonWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });

    }

    private void loadBeltData(String beltName){
        Intent intent = new Intent(BeltsActivity.this, ViewBeltActivity.class);

        intent.putExtra("beltName", beltName);

        startActivity(intent);
    }
}
