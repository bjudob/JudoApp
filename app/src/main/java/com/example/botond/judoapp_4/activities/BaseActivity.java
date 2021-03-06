package com.example.botond.judoapp_4.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.auth.MainActivity;
import com.example.botond.judoapp_4.activities.learn.LearnActivity;
import com.example.botond.judoapp_4.activities.profile.ProfileActivity;
import com.example.botond.judoapp_4.activities.scoreboard.ScoreboardActivity;
import com.google.firebase.auth.FirebaseAuth;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId()); //this line allocates 200mb


        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationInclude);
        if(navigationView!=null){
            navigationView.setOnNavigationItemSelectedListener(this);
        }

        toolbar=(Toolbar) findViewById(R.id.toolbarInclude);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (itemId == R.id.navigation_scoreboard) {
                startActivity(new Intent(this, ScoreboardActivity.class));
            } else if (itemId == R.id.navigation_learn) {
                startActivity(new Intent(this, LearnActivity.class));
            }
            finish();
        }, 0); //300
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);

        return true;
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

            case R.id.menuSettings:

                Intent i = new Intent(this, ProfileSettingsActivity.class);
                startActivity(i);
                break;
        }

        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    protected  void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }
        }
    }

    abstract protected int getContentViewId();

    abstract protected int getNavigationMenuItemId();

    @Override
    public void onBackPressed() {

    }
}
