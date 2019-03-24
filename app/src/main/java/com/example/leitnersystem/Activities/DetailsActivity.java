package com.example.leitnersystem.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leitnersystem.Fragments.DetailsFragment;
import com.example.leitnersystem.Fragments.StudyFragment;
import com.example.leitnersystem.R;

public class DetailsActivity extends AppCompatActivity {


    // Keys for data passed by Intent
    String mCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent mainIntent = getIntent();
        mCategory = mainIntent.getStringExtra("Category");
        setTitle(mCategory);



        if(savedInstanceState == null) {
            // Create detailsFragment object
            DetailsFragment detailsFragment = new DetailsFragment();

            // Add fragment to its container
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction
                    .add(R.id.details_activity_container, detailsFragment)
                    .commit();
        }
    }
}
