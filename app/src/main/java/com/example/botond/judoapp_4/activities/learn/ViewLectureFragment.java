package com.example.botond.judoapp_4.activities.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.LectureController;
import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.manager.ResourceManager;

import java.util.List;

public class ViewLectureFragment extends Fragment {
    private static final String ARG_LESSON_ID = "param_lecture_id";

    private String lectureId;
    private Lecture lecture;

    private ImageView imageView;
    private TextView textViewText;
    private TextView textViewTitle;

    private LectureController lectureController;

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
        imageView=(ImageView) getView().findViewById(R.id.imageViewImg);
        textViewText=(TextView) getView().findViewById(R.id.textViewText);
        textViewTitle=(TextView) getView().findViewById(R.id.textViewTitle);

        lectureController= ResourceManager.getLectureController();

        lecture=lectureController.getLecture(lectureId);

        if(lecture==null){
            //error, data not found, go back
            // TODO: 30.05.2018
        }
        else{
            textViewTitle.setText(lecture.getTitle());
            textViewText.setText(lecture.getText());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
