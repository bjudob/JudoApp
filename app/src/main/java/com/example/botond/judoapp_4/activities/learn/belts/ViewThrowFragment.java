package com.example.botond.judoapp_4.activities.learn.belts;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewThrowFragment extends Fragment {
    private static final String ARG_PARAM_BELT_NAME = "paramBeltName";
    private static final String ARG_PARAM_THROW_ID = "paramThrowId";

    private String beltName;
    private int throwIndex;
    private Belt belt;
    private List<Throw> throwList;
    private Throw currentThrow;
    private BeltController beltController;

    private TextView textViewThrowName, textViewNr;
    private ImageView imageViewThrow, imageViewBelt;
    private Button buttonPrev,buttonNext;

    private ViewThrowFragment.OnFragmentInteractionListener mListener;

    public ViewThrowFragment() {
        // Required empty public constructor
    }


    public static ViewThrowFragment newInstance(String beltName, int throwIndex) {
        ViewThrowFragment fragment = new ViewThrowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_BELT_NAME, beltName);
        args.putInt(ARG_PARAM_THROW_ID, throwIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            beltName = getArguments().getString(ARG_PARAM_BELT_NAME);
            throwIndex = getArguments().getInt(ARG_PARAM_THROW_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_throw, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        textViewThrowName=(TextView) getView().findViewById(R.id.textViewThrowName);
        textViewNr=(TextView) getView().findViewById(R.id.textViewNr);
        imageViewThrow=(ImageView) getView().findViewById(R.id.imageViewThrow);
        imageViewBelt=(ImageView) getView().findViewById(R.id.imageViewBelt);
        buttonPrev=(Button) getView().findViewById(R.id.buttonPrevious);
        buttonNext=(Button) getView().findViewById(R.id.buttonNext);


        beltController= ResourceManager.getBeltController();

        belt=beltController.getByName(beltName);


        if(belt==null){
            //error, data not found, go back
            // TODO: 30.05.2018
        }
        else{
            textViewThrowName.setText(belt.getName());
            loadBeltImage();

            throwList=belt.getThrowList();
            currentThrow=throwList.get(throwIndex);

            loadThrow();

            buttonPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next(-1);
                }
            });
            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next(1);
                }
            });
        }
    }

    private void loadThrow(){
        textViewThrowName.setText(currentThrow.getName());

        Glide.with(this).load(currentThrow.getImageUrl()).into(imageViewThrow);

        String nrText=((Integer)(throwIndex+1)).toString();
        nrText+="/";
        nrText+=((Integer)(throwList.size())).toString();

        textViewNr.setText(nrText);
    }

    private void next(int i){
        throwIndex=(throwIndex+i)%throwList.size();
        if (throwIndex<0) throwIndex += throwList.size();

        currentThrow=throwList.get(throwIndex);

        loadThrow();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ViewThrowFragment.OnFragmentInteractionListener) {
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

    public interface OnFragmentInteractionListener {

    }
}
