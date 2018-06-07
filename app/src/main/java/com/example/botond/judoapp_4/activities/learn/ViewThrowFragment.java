package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewThrowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewThrowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewThrowFragment extends Fragment {private static final String ARG_PARAM_BELT_NAME = "param1";

    private String beltName;
    private Belt belt;
    private BeltController beltController;
    private TextView textViewBeltName;
    private ListView listViewThrows;

    private ViewThrowFragment.OnFragmentInteractionListener mListener;

    public ViewThrowFragment() {
        // Required empty public constructor
    }


    public static ViewThrowFragment newInstance(String param1) {
        ViewThrowFragment fragment = new ViewThrowFragment();
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

            final ArrayAdapter adapter = new ArrayAdapter<Throw>(this.getContext(),
                    R.layout.listview_elem, belt.getThrowList());

            listViewThrows.setAdapter(adapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ViewBeltFragment.OnFragmentInteractionListener) {
            mListener = (ViewThrowFragment.OnFragmentInteractionListener) context;
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
