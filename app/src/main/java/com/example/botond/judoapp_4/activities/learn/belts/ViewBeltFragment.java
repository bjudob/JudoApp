package com.example.botond.judoapp_4.activities.learn.belts;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewBeltFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewBeltFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewBeltFragment extends Fragment {

    private static final String ARG_PARAM_BELT_NAME = "paramBeltName";

    private String beltName;
    private Belt belt;
    private BeltController beltController;
    private TextView textViewBeltName;
    private ListView listViewThrows;

    private OnFragmentInteractionListener mListener;

    public ViewBeltFragment() {
        // Required empty public constructor
    }


    public static ViewBeltFragment newInstance(String param1) {
        ViewBeltFragment fragment = new ViewBeltFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_BELT_NAME, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            beltName = getArguments().getString(ARG_PARAM_BELT_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_belt, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        textViewBeltName=(TextView) getView().findViewById(R.id.textViewBeltName);
        listViewThrows=(ListView) getView().findViewById(R.id.listViewThrows);

        beltController= ResourceManager.getBeltController();

        belt=beltController.getByName(beltName);

        if(belt==null){
            //error, data not found, go back
            // TODO: 30.05.2018
        }
        else{
            textViewBeltName.setText(belt.getName());

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
        void showThrow(String beltName, int throwIndex);
    }
}
