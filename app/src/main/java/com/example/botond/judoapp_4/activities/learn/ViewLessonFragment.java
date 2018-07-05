package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.domain.Lecture;

public class ViewLessonFragment extends Fragment {
    private static final String ARG_LESSON_ID = "param_lecture_id";

    private String lectureId;
    private Lecture lecture;

    public ViewLessonFragment() {
        // Required empty public constructor
    }

    public static ViewLessonFragment newInstance(String lecture) {
        ViewLessonFragment fragment = new ViewLessonFragment();
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
    public void onDetach() {
        super.onDetach();
    }
}
