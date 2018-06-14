package com.example.botond.judoapp_4.activities.learn.belts;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;
import com.squareup.picasso.Picasso;

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
    private ListView listViewThrows;
    private ImageView imageViewBelt;

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

    public void loadBeltImage(){
        switch (belt.getName()){
            case "white":
                setBeltImage(R.drawable.belt_white);
                break;
            case "yellow":
                setBeltImage(R.drawable.belt_yellow);
                break;
            case "orange":
                setBeltImage(R.drawable.belt_orange);
                break;
            case "green":
                setBeltImage(R.drawable.belt_green);
                break;
            case "blue":
                setBeltImage(R.drawable.belt_blue);
                break;
            case "brown":
                setBeltImage(R.drawable.belt_brown);
                break;
        }
    }

    public void setBeltImage(int img) {
        Picasso.with(getContext()).load(img).into(imageViewBelt);

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
