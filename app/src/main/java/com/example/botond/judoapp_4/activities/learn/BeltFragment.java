package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.learn.belts.BeltsActivity;
import com.example.botond.judoapp_4.activities.learn.belts.ViewBeltActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BeltFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BeltFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeltFragment extends Fragment {

    private Button buttonWhite,buttonYellow,buttonOrange,buttonGreen,buttonBlue,buttonBrown,buttonBlack;
    private BeltFragment.OnFragmentInteractionListener mListener;
    private Button techniquesButton;

    public BeltFragment() {
        // Required empty public constructor
    }

    public static BeltFragment newInstance(String param1, String param2) {
        BeltFragment fragment = new BeltFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_belt, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        buttonWhite=(Button) getView().findViewById(R.id.buttonWhite);
        buttonYellow=(Button) getView().findViewById(R.id.buttonYellow);
        buttonOrange=(Button) getView().findViewById(R.id.buttonOrange);
        buttonGreen=(Button) getView().findViewById(R.id.buttonGreen);
        buttonBlue=(Button) getView().findViewById(R.id.buttonBlue);
        buttonBrown=(Button) getView().findViewById(R.id.buttonBrown);
        buttonBlack=(Button) getView().findViewById(R.id.buttonBlack);

        buttonWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
        buttonBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBeltData("white");
            }
        });
    }

    private void loadBeltData(String beltName){
        ((LearnActivity)getActivity()).showBelt(beltName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BeltFragment.OnFragmentInteractionListener) {
            mListener = (BeltFragment.OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
