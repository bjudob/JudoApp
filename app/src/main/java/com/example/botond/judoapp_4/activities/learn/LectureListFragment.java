package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.LectureController;
import com.example.botond.judoapp_4.domain.Lecture;
import com.example.botond.judoapp_4.domain.LectureCategory;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;

import java.util.List;

public class LectureListFragment extends Fragment {
    private static final String ARG_PARAM_CATEGORY = "paramLessonCategory";
    
    private String lectureCategoryName;
    private LectureCategory lectureCategory;
    private TextView textViewTitle;
    private ListView listViewLectures;
    private LectureController lectureController;
    private List<Lecture> lectures;

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
            lectureCategoryName = getArguments().getString(ARG_PARAM_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        textViewTitle=(TextView) getView().findViewById(R.id.textViewTitle);
        listViewLectures=(ListView) getView().findViewById(R.id.listViewLessons);

        lectureController= ResourceManager.getLectureController();

        lectureCategory=lectureController.getByName(lectureCategoryName);

        if(lectureCategory==null){
            //error, data not found, go back

        }
        else{
            textViewTitle.setText(lectureCategoryName.toUpperCase());

            lectures=lectureCategory.getLectures();

            final ArrayAdapter adapter = new ArrayAdapter<Lecture>(this.getContext(),
                    R.layout.listview_elem, lectures);

            listViewLectures.setAdapter(adapter);

            listViewLectures. setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {

                    mListener.showLecture(lectures.get(position).getTitle());
                }
            });
        }
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
