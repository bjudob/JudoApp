package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.domain.Vocabulary;
import com.example.botond.judoapp_4.domain.VocabularyEntry;
import com.example.botond.judoapp_4.manager.ResourceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DictionaryFragment extends Fragment {

    String JPN="jpn",ENG="eng";

    ListView listViewDictionary;
    Spinner spinnerDictionary;

    List<Vocabulary> vocabularies;
    List<String> vocabularyNames;

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

        ResourceManager.init();
    }

    @Override
    public void onStart() {
        super.onStart();

        listViewDictionary=(ListView)getView().findViewById(R.id.listViewDictionary);
        spinnerDictionary=(Spinner) getView().findViewById(R.id.spinnerDictionary);


        vocabularies=ResourceManager.getVocabularyController().getVocabularies();

        setUpDropdown();
        spinnerDictionary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loadDictionaryList(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    private void setUpDropdown(){
        vocabularyNames=new ArrayList<>();
        for (Vocabulary v:vocabularies) {
            vocabularyNames.add(v.getName());
        }

        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.item_dropdown, vocabularyNames);

        spinnerDictionary.setAdapter(dropdownAdapter);
    }

    private void loadDictionaryList(int i){
        List<VocabularyEntry> vocabularyEntryList=vocabularies.get(i).getVocabularyEntries();

        List<HashMap<String,String>> listItems=new ArrayList<>();

        SimpleAdapter adapter=new SimpleAdapter(
                getContext(),
                listItems,
                R.layout.item_list,
                new String[]{JPN,ENG},
                new int[]{R.id.text1,R.id.text2});

        for(VocabularyEntry ve:vocabularyEntryList){
            HashMap<String,String> resultMap=new HashMap<>();
            resultMap.put(JPN, ve.getJpn());
            resultMap.put(ENG, ve.getEng());

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
