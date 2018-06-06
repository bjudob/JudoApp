package com.example.botond.judoapp_4.activities.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.manager.ResourceManager;
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

public class ProfilePresenter implements ProfileMVP.presenter{

    private static ProfilePresenter instance;

    private ProfileMVP.view view;
    private Context context;

    private String profileImageUrl;
    private String displayNameKey, beltKey;
    private FirebaseAuth mAuth;

    public ProfilePresenter(ProfileMVP.view view, Context context){
        ResourceManager.init();

        this.view=view;
        this.context=context;

        mAuth=FirebaseAuth.getInstance();

        displayNameKey=context.getString(R.string.pref_display_name);
    }

    public static ProfileMVP.presenter getInstance(ProfileMVP.view view, Context context){
        if(instance==null){
            instance=new ProfilePresenter(view,context);
        }
        else{
            instance.view=view;
            instance.context=context;
        }
        return instance;
    }

    @Override
    public void reset(){
        context=null;
        view=null;
    }

    @Override
    public void loadUserInfo(){
        if(view!=null) {
            view.setProgressBarVisibility(View.VISIBLE);
        }
        final FirebaseUser user=mAuth.getCurrentUser();

        if(user!=null && context!=null) {
            SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(context);
            String displayname=prefs.getString(displayNameKey, null);

            if(displayname!=null && !displayname.equals(user.getDisplayName())){
                saveUserInfo(displayname);
            }
            if(user.getPhotoUrl()!=null){
                String photoUrl = user.getPhotoUrl().toString();
                if(view!=null) {
                    view.loadProfilePicture(photoUrl);
                }
            }

            if(user.getDisplayName()!=null) {
                String displayName = user.getDisplayName();
                if(view!=null){
                    view.setUsername(displayName);
                }
            }

            if(user.isEmailVerified()){

            }
            else{
                if(view!=null){
                    view.setEmail("Email not verified! (Click to Verify)");
                }
            }
        }


        if(view!=null){
            view.setProgressBarVisibility(View.GONE);
        }
    }

    @Override
    public void saveUserInfo(String displayName){

        FirebaseUser user=mAuth.getCurrentUser();

        if(user!=null){
            if(profileImageUrl!=null) {
                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .setPhotoUri(Uri.parse(profileImageUrl))
                        .build();
                user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            loadUserInfo();
                            if(view!=null){
                                view.showToast("Profile Updated");
                            }

                        } else {
                            if(view!=null){
                                view.showToast("Update failed");
                            }
                        }
                    }
                });
            }
            else{
                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build();
                user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            if(view!=null){
                                view.setUsername(displayName);
                            }

                        } else {
                            //Toast.makeText(ProfileActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void uploadImageToFirebaseStorage(Uri uriProfileImage){
        if(view!=null){
            view.setProgressBarVisibility(View.VISIBLE);
        }

        String refString="profilepics/"+System.currentTimeMillis()+".jpg";
        final StorageReference profileImageRef= FirebaseStorage.getInstance().getReference(refString);

        if(uriProfileImage!=null){
            profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if(view!=null){
                        view.setProgressBarVisibility(View.GONE);
                    }

                    profileImageUrl=taskSnapshot.getDownloadUrl().toString();
                    saveUserInfo(null);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(view!=null){
                                view.setProgressBarVisibility(View.GONE);
                            }

                            if(view!=null){
                                view.showToast(e.getMessage());
                            }
                        }
                    });
        }
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    @Override
    public void loadBelt(){
        if(context!=null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            String belt = prefs.getString("belt_list", null);

            if (belt != null) {
                switch (belt) {
                    case "belt_white":
                        setBeltImage(R.drawable.belt_white);
                        break;
                    case "belt_yellow":
                        setBeltImage(R.drawable.belt_yellow);
                        break;
                    case "belt_orange":
                        setBeltImage(R.drawable.belt_orange);
                        break;
                    case "belt_green":
                        setBeltImage(R.drawable.belt_green);
                        break;
                    case "belt_blue":
                        setBeltImage(R.drawable.belt_blue);
                        break;
                    case "belt_brown":
                        setBeltImage(R.drawable.belt_brown);
                        break;
                    case "belt_black":
                        setBeltImage(R.drawable.belt_black);
                        break;
                }
            }
        }

    }

    private void setBeltImage(int imgId){
        if(view!=null && context!=null){
            view.setBeltImage(context.getDrawable(imgId));
        }
    }

}
