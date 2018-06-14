package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.botond.judoapp_4.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DictionaryFragment extends Fragment {

    ListView listViewDictionary;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    public static DictionaryFragment newInstance() {
        DictionaryFragment fragment = new DictionaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        listViewDictionary=(ListView)getView().findViewById(R.id.listViewDictionary);

        HashMap<String,String> hashMapDictionary=new HashMap<>();

        hashMapDictionary.put("dojo", "terem");
        hashMapDictionary.put("sensei", "tanar");
        hashMapDictionary.put("judogi", "judo ruha");
        hashMapDictionary.put("tatami", "szonyeg");
        hashMapDictionary.put("dojo", "terem");

        List<HashMap<String,String>> listItems=new ArrayList<>();

        SimpleAdapter adapter=new SimpleAdapter(
                getContext(),
                listItems,
                R.layout.list_item,
                new String[]{"First line","Second line"},
                new int[]{R.id.text1,R.id.text2});

        Iterator it=hashMapDictionary.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String,String> resultMap=new HashMap<>();
            Map.Entry pair=(Map.Entry) it.next();
            resultMap.put("First line", pair.getKey().toString());
            resultMap.put("Second line", pair.getValue().toString());

            listItems.add(resultMap);
        }

        listViewDictionary.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
