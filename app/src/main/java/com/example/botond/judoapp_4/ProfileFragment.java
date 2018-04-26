package com.example.botond.judoapp_4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private static final int CHOOSE_IMAGE = 101;

    TextView textViewEmailVerified;
    ImageView imageView;
    EditText editTextUsername;
    String profileImageUrl;

    ProgressBar progressBar;

    FirebaseAuth mAuth;
    Uri uriProfileImage;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();

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
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth=FirebaseAuth.getInstance();

        imageView=(ImageView) view.findViewById(R.id.imageViewCamera);
        editTextUsername =(EditText) view.findViewById(R.id.editTextCamera);
        progressBar=(ProgressBar) view.findViewById(R.id.progressbarProfileImage);
        textViewEmailVerified=(TextView) view.findViewById(R.id.textViewVerifiedEmail);
        Toolbar toolbar=(Toolbar) view.findViewById(R.id.toolbarProfile);

        AppCompatActivity aca=((AppCompatActivity)getActivity());
        aca.setSupportActionBar(toolbar);

        loadUserInfo();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });

        view.findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
                    //+ " must implement OnFragmentInteractionListener");
            Toast.makeText(context, "Profile Fragment attached",Toast.LENGTH_SHORT);
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

    private void loadUserInfo(){
        progressBar.setVisibility(View.VISIBLE);
        final FirebaseUser user=mAuth.getCurrentUser();

        if(user!=null) {
            if(user.getPhotoUrl()!=null){
                String photoUrl = user.getPhotoUrl().toString();
                Glide.with(this)
                        .load(photoUrl)
                        .into(imageView);
            }

            if(user.getDisplayName()!=null) {
                String displayName = user.getDisplayName();
                editTextUsername.setText(displayName);
            }

            if(user.isEmailVerified()){
                textViewEmailVerified.setText("Email verified!");
            }
            else{
                textViewEmailVerified.setText("Email not verified! (Click to Verify)");

                textViewEmailVerified.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getActivity(), "Verification email sent!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        }


        progressBar.setVisibility(View.GONE);
    }

    private void saveUserInfo(){
        String displayName= editTextUsername.getText().toString();

        if(displayName.isEmpty()){
            editTextUsername.setError("Name required");
            editTextUsername.requestFocus();
            return;
        }

        FirebaseUser user=mAuth.getCurrentUser();

        if(user!=null && profileImageUrl!=null){
            UserProfileChangeRequest profileChangeRequest=new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();
            user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getActivity(),"Profile Updated",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getActivity(),"Update Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showImageChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"),CHOOSE_IMAGE);
    }

    private void uploadImageToFirebaseStorae(){
        progressBar.setVisibility(View.VISIBLE);

        String refString="profilepics/"+System.currentTimeMillis()+".jpg";
        final StorageReference profileImageRef= FirebaseStorage.getInstance().getReference(refString);

        if(uriProfileImage!=null){
            profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.GONE);

                    profileImageUrl=taskSnapshot.getDownloadUrl().toString();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
