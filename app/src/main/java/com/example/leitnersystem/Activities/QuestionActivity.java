package com.example.leitnersystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.leitnersystem.Fragments.QuestionFragment;
import com.example.leitnersystem.R;

public class QuestionActivity extends AppCompatActivity {

    String LOGTAG = "QuestionActivity";
    String keyCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent mainIntent = getIntent();
        keyCategory = mainIntent.getStringExtra("Category");

        Log.d(LOGTAG, "Launch");
        Log.d(LOGTAG, "keyCategory = " + keyCategory);

        // Sets title on app bar, easy to tell what category you are in.
        setTitle(keyCategory);
        // Question fragment object.
        QuestionFragment questionFragment = new QuestionFragment();


        Bundle bundle = new Bundle();
        bundle.putString("category", keyCategory);
        // Passes keyCategory to Fragment, so the adapter can display questions from the db
        // that match the keyCategory
        questionFragment.setArguments(bundle);

        // Add fragment to its container with Fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction
                .add(R.id.activity_question_container, questionFragment)
                .commit();
    }
}
