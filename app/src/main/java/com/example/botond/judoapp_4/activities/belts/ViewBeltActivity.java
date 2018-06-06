package com.example.botond.judoapp_4.activities.belts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;

public class ViewBeltActivity extends AppCompatActivity {

    private Belt belt;
    private BeltController beltController;
    private TextView textViewBeltName;
    private ListView listViewThrows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_belt);

        Intent intent = getIntent();
        String beltName = intent.getStringExtra("beltName");

        textViewBeltName=(TextView) findViewById(R.id.textViewBeltName);
        listViewThrows=(ListView) findViewById(R.id.listViewThrows);

        beltController= ResourceManager.getBeltController();

        belt=beltController.getByName(beltName);

        if(belt==null){
            //error, data not found, go back
            // TODO: 30.05.2018
        }
        else{
            textViewBeltName.setText(belt.getName());

            final ArrayAdapter adapter = new ArrayAdapter<Throw>(this,
                    R.layout.listview_elem, belt.getThrowList());

            listViewThrows.setAdapter(adapter);
        }
    }
}
