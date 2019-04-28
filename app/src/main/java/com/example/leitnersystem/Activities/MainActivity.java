package com.example.leitnersystem.Activities;

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

public class MainActivity extends AppCompatActivity {
    private String LOGTAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final String LOGTAG = "MainActivity";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

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


}
