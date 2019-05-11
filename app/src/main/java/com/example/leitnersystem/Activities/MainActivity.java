package com.example.leitnersystem.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.leitnersystem.Fragments.CategoryFragment;
import com.example.leitnersystem.Fragments.InstructionFragment;
import com.example.leitnersystem.R;
import com.google.android.gms.ads.MobileAds;

@SuppressWarnings("SwitchStatementWithTooFewBranches")
public class MainActivity extends AppCompatActivity {
    private final String LOGTAG = "MainActivity";

    /**
     * onCreate Initialize Activity.
     *
     * @param savedInstanceState data saved from previous use.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(LOGTAG, "MainActivity");

        //Only create new fragment when there is no previously saved state
        if(savedInstanceState == null) {

            // AdMob initialize, only has to be done once.
            MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

            // Create a new categoryFragment
            CategoryFragment categoryFragment = new CategoryFragment();
            // Load Fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .add(R.id.activity_main_container, categoryFragment)
                    .commit();

        }

    }

    /**
     * onCreateOptionsMenu Initializes the contents of the menu options.
     *
     * @param menu the options menu in which you place your items.
     * @return must return true in order to display the Menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * onOptionsItemSelected is called when an item in the options menu is selected.
     *
     * @param item The menu item selected, Item can't be null.
     * @return Returns false to allow normal menu processing to proceed, when item is successfully
     * handled it returns true.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()) {
            case R.id.menu_instruction:
                Log.d(LOGTAG, "Menu Instructions");
                InstructionFragment instructionFragment = new InstructionFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft
                        .replace(R.id.activity_main_container, instructionFragment)
                        .addToBackStack(null)
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * StartActivity is a special variation to launch the Activity, used to provide alter the start
     * animation.
     *
     * @param intent, the intent to start. The value must never be null.
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * finish call this when you activity is done and should be closed. Used to include animation
     * when exiting activity.
     */
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
