package com.example.botond.judoapp_4.belts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.domain.Belt;

public class ViewBeltActivity extends AppCompatActivity {

    private Belt belt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_belt);

    }
}
