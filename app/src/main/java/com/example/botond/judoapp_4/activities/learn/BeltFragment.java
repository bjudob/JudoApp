package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.botond.judoapp_4.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BeltFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BeltFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeltFragment extends Fragment {

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

        techniquesButton=(Button) getView().findViewById(R.id.buttonTechniques);

        techniquesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                techniquesButtonClick();
            }
        });
    }

    public void techniquesButtonClick() {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }


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
