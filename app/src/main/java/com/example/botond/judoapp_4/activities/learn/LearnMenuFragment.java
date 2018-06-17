package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.botond.judoapp_4.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LearnMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LearnMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnMenuFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button buttonBasics, buttonTechniques, buttonDictionary, buttonFalling, buttonKata;

    public LearnMenuFragment() {
        // Required empty public constructor
    }

    public static LearnMenuFragment newInstance() {
        LearnMenuFragment fragment = new LearnMenuFragment();

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
        return inflater.inflate(R.layout.fragment_learn_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        buttonBasics =(Button) getView().findViewById(R.id.buttonBasics);
        buttonTechniques =(Button) getView().findViewById(R.id.buttonTechniques);
        buttonDictionary =(Button) getView().findViewById(R.id.buttonDictionary);
        buttonFalling =(Button) getView().findViewById(R.id.buttonFalling);
        buttonKata =(Button) getView().findViewById(R.id.buttonKata);

        buttonBasics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.buttonBasicsClick();
                }
            }
        });
        buttonTechniques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.buttonTechniquesClick();
                }
            }
        });
        buttonDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.buttonDictionaryClick();
                }
            }
        });
        buttonFalling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.buttonFallingClick();
                }
            }
        });
        buttonKata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.buttonKataClick();
                }
            }
        });
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
        void buttonBasicsClick();
        void buttonTechniquesClick();
        void buttonDictionaryClick();
        void buttonFallingClick();
        void buttonKataClick();
    }
}
