package com.example.botond.judoapp_4.activities.learn;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;

import java.util.List;

public class ViewLectureFragment extends Fragment {
    private static final String ARG_LESSON_ID = "param_lecture_id";

    private String lectureId;
    private Lecture lecture;

    public ViewLectureFragment() {
        // Required empty public constructor
    }

    public static ViewLectureFragment newInstance(String lecture) {
        ViewLectureFragment fragment = new ViewLectureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LESSON_ID, lecture);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lectureId = getArguments().getString(ARG_LESSON_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_lesson, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        imageViewBelt=(ImageView) getView().findViewById(R.id.imageViewBelt);
        listViewThrows=(ListView) getView().findViewById(R.id.listViewThrows);

        beltController= ResourceManager.getBeltController();

        belt=beltController.getByName(beltName);

        if(belt==null){
            //error, data not found, go back
            // TODO: 30.05.2018
        }
        else{
            loadBeltImage();

            List<Throw> throwList=belt.getThrowList();

            final ArrayAdapter adapter = new ArrayAdapter<Throw>(this.getContext(),
                    R.layout.listview_elem, throwList);

            listViewThrows.setAdapter(adapter);

            listViewThrows. setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    String beltName=belt.getName();

                    mListener.showThrow(beltName, position);
                }
            });
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
