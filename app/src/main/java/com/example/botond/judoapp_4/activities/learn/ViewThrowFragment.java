package com.example.botond.judoapp_4.activities.learn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.ctrl.BeltController;
import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.domain.Throw;
import com.example.botond.judoapp_4.manager.ResourceManager;
import com.felipecsl.gifimageview.library.GifImageView;

import java.util.List;

public class ViewThrowFragment extends Fragment {
    private static final String ARG_PARAM_BELT_NAME = "paramBeltName";
    private static final String ARG_PARAM_THROW_ID = "paramThrowId";

    private String beltName;
    private int throwIndex;

    private Belt belt;
    private List<Throw> throwList;
    private BeltController beltController;
    private TextView textViewThrowName;
    private GifImageView gifImageViewThrow;
    private ImageView imageViewThrow;
    private Throw currentThrow;

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
        gifImageViewThrow=(GifImageView) getView().findViewById(R.id.gifImageView);
        imageViewThrow=(ImageView) getView().findViewById(R.id.imageViewThrow);

        beltController= ResourceManager.getBeltController();

        belt=beltController.getByName(beltName);


        if(belt==null){
            //error, data not found, go back
            // TODO: 30.05.2018
        }
        else{
            textViewThrowName.setText(belt.getName());

            throwList=belt.getThrowList();
            currentThrow=throwList.get(throwIndex);
            textViewThrowName.setText(currentThrow.getName());

            //GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
            Glide.with(this).load(currentThrow.getImageUrl()).into(imageViewThrow);

        }
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

    public interface OnFragmentInteractionListener {

    }
}
