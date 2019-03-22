package com.example.leitnersystem.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leitnersystem.Fragments.CategoryFragment;
import com.example.leitnersystem.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Only create new fragment when there is no previously saved state
        if(savedInstanceState == null) {

            // Create a new categoryFragment
            CategoryFragment categoryFragment = new CategoryFragment();

            // Add the fragment to its container with FragmentManager
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction
                    .add(R.id.activity_main_container, categoryFragment)
                    .commit();


        }


    }
}