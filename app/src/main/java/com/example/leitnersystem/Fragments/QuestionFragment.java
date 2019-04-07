package com.example.leitnersystem.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leitnersystem.Adapters.QuestionAdapter;
import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.Question;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;

import java.util.List;

import butterknife.ButterKnife;

public class QuestionFragment extends Fragment {

    String LOGTAG = "QuestionFragment";
    private QuestionViewModel mQuestionViewModel;


    // Fragment requires empty constructor.
    public QuestionFragment() {

    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate fragment_question_layout.
        View view = inflater.inflate(R.layout.fragment_question_layout, container, false);

        // ButterKnife
        ButterKnife.bind(this, view);


        // RecyclerVew, select layout
        RecyclerView recyclerView = view .findViewById(R.id.rv_question);

        // Adapter select handler.
        final QuestionAdapter adapter = new QuestionAdapter(getActivity());

        // Set adapter to RecyclerView
        recyclerView.setAdapter(adapter);

        // Set LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get new or existing ViewModel from the View Model provider
        mQuestionViewModel = ViewModelProviders.of(getActivity()).get(QuestionViewModel.class);

        // Bundle can be retrieved in onCreate and onCreateView
        String mCategory = this.getArguments().getString("category");

        Log.d(LOGTAG, "Category " + mCategory);
        mQuestionViewModel.findCategory(mCategory).observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                adapter.setQuestion(questions);
            }
        });



                return view;
    }
}
