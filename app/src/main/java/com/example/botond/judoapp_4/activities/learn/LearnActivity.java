package com.example.botond.judoapp_4.activities.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.botond.judoapp_4.R;
import com.example.botond.judoapp_4.activities.BaseActivity;
import com.example.botond.judoapp_4.activities.learn.belts.BeltFragment;
import com.example.botond.judoapp_4.activities.learn.belts.ViewBeltFragment;
import com.example.botond.judoapp_4.activities.learn.belts.ViewThrowFragment;
import com.example.botond.judoapp_4.activities.learn.dictionary.DictionaryFragment;
import com.example.botond.judoapp_4.manager.ResourceManager;

public class LearnActivity extends BaseActivity implements
        LearnMenuFragment.OnFragmentInteractionListener,
        BeltFragment.OnFragmentInteractionListener,
        ViewBeltFragment.OnFragmentInteractionListener,
        ViewThrowFragment.OnFragmentInteractionListener,
        LectureListFragment.OnFragmentInteractionListener{

    //private Button basicsButton, techniquesButton, kataButton;
    private FrameLayout frameLayout;
    private Fragment fragmentCurrent;

    private String lastBeltName;
    private boolean falling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_learn);

        ResourceManager.init();

        frameLayout=(FrameLayout) findViewById(R.id.mainFrame);

        LearnMenuFragment menuFragment = LearnMenuFragment.newInstance();

        changeFragment(menuFragment);
    }

    private void changeFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment,
                LearnMenuFragment.class.getName());

        // Commit the transaction
        fragmentTransaction.commit();

        fragmentCurrent=fragment;
        falling=false;
    }

    @Override
    public void buttonBasicsClick() {
        LectureListFragment lectureListFragment=LectureListFragment.newInstance("basics");

        changeFragment(lectureListFragment);
    }

    @Override
    public void buttonTechniquesClick() {
        BeltFragment beltFragment=BeltFragment.newInstance();

        changeFragment(beltFragment);
    }

    @Override
    public void buttonDictionaryClick() {
        DictionaryFragment dictionaryFragment=DictionaryFragment.newInstance();

        changeFragment(dictionaryFragment);
    }

    @Override
    public void buttonFallingClick() {
        showThrow("falling",0);

        falling=true;
    }

    @Override
    public void buttonKataClick() {

    }

    @Override
    public void showBelt(String beltName) {
        lastBeltName=beltName;
        ViewBeltFragment viewBeltFragment=ViewBeltFragment.newInstance(beltName);

        changeFragment(viewBeltFragment);
    }

    @Override
    public void showThrow(String beltName, int throwIndex) {
        ViewThrowFragment viewThrowFragment=ViewThrowFragment.newInstance(beltName, throwIndex);

        changeFragment(viewThrowFragment);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_learn;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_learn;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(fragmentCurrent instanceof BeltFragment){
            LearnMenuFragment menuFragment = LearnMenuFragment.newInstance();

            changeFragment(menuFragment);
        }

        if(fragmentCurrent instanceof ViewBeltFragment){
            BeltFragment beltFragment = BeltFragment.newInstance();

            changeFragment(beltFragment);
        }

        if(fragmentCurrent instanceof ViewThrowFragment){
            Fragment fragment;
            if(falling) {
                fragment = LearnMenuFragment.newInstance();
            }
            else{
                fragment = ViewBeltFragment.newInstance(lastBeltName);
            }
            changeFragment(fragment);
        }

        if(fragmentCurrent instanceof DictionaryFragment){
            LearnMenuFragment menuFragment = LearnMenuFragment.newInstance();

            changeFragment(menuFragment);
        }
    }

    @Override
    public void showLecture(String id) {
        
    }
}
