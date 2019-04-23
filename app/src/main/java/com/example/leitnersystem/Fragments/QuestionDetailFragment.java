package com.example.leitnersystem.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.Question;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionDetailFragment extends Fragment {

    private final String LOGTAG = "QuestionDetailFragment";


    @BindView(R.id.tv_question_details_question)
    TextView tvQuestion;
    @BindView(R.id.tv_question_details_answer)
    TextView tvAnswer;
    @BindView(R.id.tv_question_details_box)
    TextView tvBox;
    @BindView(R.id.ad_view)
    AdView adView;

    private int mQuestionNumber;

    private int mId;
    private String mQuestion;
    private String mAnswer;
    private int mBox;
    private int mCounter;

    // Constructor, empty
    public QuestionDetailFragment(){
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle onSavedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_details_layout, parent, false);


        Log.d(LOGTAG, "onCreateView");

        // ButterKnife
        ButterKnife.bind(this, view);

        // AdMob
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        // ViewModel
        QuestionViewModel mQuestionViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(QuestionViewModel.class);

        // Question number
        Bundle bundle = getArguments();
        mQuestionNumber = Objects.requireNonNull(bundle).getInt("Key_QuestionNumber");
        Log.d(LOGTAG, "QuestionNumber " + mQuestionNumber);

        final String category = mQuestionViewModel.getTextText();
        Log.d(LOGTAG, "Category " + category);


        mQuestionViewModel.findCategory(category).observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                mId = Objects.requireNonNull(questions).get(mQuestionNumber).getId();
                mQuestion = questions.get(mQuestionNumber).getQuestion();
                mAnswer = questions.get(mQuestionNumber).getAnswer();
                mBox = questions.get(mQuestionNumber).getBox();


                Log.d(LOGTAG,
                        "ID: " + String.valueOf(mId) +
                                " Question: " + mQuestion +
                                " Answer: " + mAnswer +
                                " Box: " + mBox);


                tvQuestion.setText(mQuestion);
                tvAnswer.setText(mAnswer);
                tvBox.setText(String.valueOf(mBox));


            }
        });

        return view;
    }
}
