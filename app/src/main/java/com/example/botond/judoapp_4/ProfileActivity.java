package com.example.botond.judoapp_4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import java.io.IOException;

public class ProfileActivity extends BaseActivity {

    private static final int CHOOSE_IMAGE = 101;

    TextView textViewEmailVerified;
    ImageView imageView;
    EditText editTextUsername;
    String profileImageUrl;
    Button buttonEdit, buttonSaveProfile;

    ProgressBar progressBar;

    FirebaseAuth mAuth;
    Uri uriProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);

        mAuth=FirebaseAuth.getInstance();

        imageView=(ImageView) findViewById(R.id.imageViewCamera);
        editTextUsername =(EditText) findViewById(R.id.editTextCamera);
        progressBar=(ProgressBar) findViewById(R.id.progressbarProfileImage);
        textViewEmailVerified=(TextView) findViewById(R.id.textViewVerifiedEmail);
        buttonEdit=(Button) findViewById(R.id.buttonEditUsername);
        buttonSaveProfile=(Button) findViewById(R.id.buttonSave);

        loadUserInfo();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });

        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo();
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editTextUsername.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextUsername.setEnabled(true);
                buttonSaveProfile.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        if(mAuth.getCurrentUser()==null){
            finish();
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        }

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_profile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uriProfileImage=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
                imageView.setImageBitmap(bitmap);

                uploadImageToFirebaseStorae();
                buttonSaveProfile.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

        return true;
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

            }
            else{
                textViewEmailVerified.setText("Email not verified! (Click to Verify)");

                textViewEmailVerified.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(ProfileActivity.this, "Verification email sent!",Toast.LENGTH_LONG).show();
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
                            Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ProfileActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ProfileActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
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
        final StorageReference profileImageRef=FirebaseStorage.getInstance().getReference(refString);

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

                            Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
