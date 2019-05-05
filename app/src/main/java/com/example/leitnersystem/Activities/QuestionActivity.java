package com.example.leitnersystem.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.leitnersystem.Fragments.InstructionFragment;
import com.example.leitnersystem.Fragments.QuestionFragment;
import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;


@SuppressWarnings("SwitchStatementWithTooFewBranches")
public class QuestionActivity extends AppCompatActivity {

    private final String LOGTAG = "QuestionActivity";
    private String mCurrentCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Prevents fragments from loading on device rotation
        if(savedInstanceState == null) {
            Intent mainIntent = getIntent();
            mCurrentCategory = mainIntent.getStringExtra("Category");

            Log.d(LOGTAG, "onCreate");
            Log.d(LOGTAG, "mCurrentCategory = " + mCurrentCategory);

            // Get new or existing ViewModel from the ViewModel provider
            QuestionViewModel mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
            mQuestionViewModel.setText(mCurrentCategory);
            mQuestionViewModel.setTextText(mCurrentCategory);

            // Sets title on app bar, easy to tell what category you are in.
            setTitle(mCurrentCategory);

            // Question fragment object.
            QuestionFragment questionFragment = new QuestionFragment();

            // Add fragment to its container with Fragment manager
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .add(R.id.activity_question_container, questionFragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().findFragmentByTag("questionStudyResultsFragment") != null) {
            Log.d(LOGTAG, "backButtonPressed if. Category = " + mCurrentCategory);

            Intent backIntent = new Intent(this, QuestionActivity.class);
            backIntent.putExtra("Category", mCurrentCategory);

            startActivity(backIntent);
            finish();

        } else {
            Log.d(LOGTAG, "backButtonPressed else");
            super.onBackPressed();
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
                        .replace(R.id.activity_question_container, instructionFragment)
                        .addToBackStack(null)
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
