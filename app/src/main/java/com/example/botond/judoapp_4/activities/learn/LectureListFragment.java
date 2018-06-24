package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.botond.judoapp_4.R;

public class LectureListFragment extends Fragment {
    private static final String ARG_PARAM_CATEGORY = "paramLessonCategory";
    
    private String lessonCategory;

    private OnFragmentInteractionListener mListener;

    public LectureListFragment() {
        // Required empty public constructor
    }


    public static LectureListFragment newInstance(String lessonCategoryName) {
        LectureListFragment fragment = new LectureListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_CATEGORY, lessonCategoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lessonCategory = getArguments().getString(ARG_PARAM_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void showLecture(String id);
    }
}
