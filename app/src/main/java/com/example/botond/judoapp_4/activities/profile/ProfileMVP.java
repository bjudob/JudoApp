package com.example.botond.judoapp_4.activities.profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

public interface ProfileMVP {
    interface view{
        void showToast(String text);
        void setBeltImage(int img);
        Context getContext();
        void setProgressBarVisibility(int visibility);
        void loadProfilePicture(String photoUrl);
        void setUsername(String username);
        void setEmail(String text);
        void showImageChooser();
    }
    interface presenter{
        void loadUserInfo();
        void loadBelt();
        void saveUserInfo(String displayName);
        void uploadImageToFirebaseStorage(Uri uriProfileImage);
        FirebaseUser getCurrentUser();
        void reset();
        void profileImageClick();
    }
}
