package com.example.leitnersystem.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.Question;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionNewQuestion extends Fragment {

    private final String LOGTAG = "QuestionNewQuestion";
    @BindView(R.id.question_btn_save)
    Button btnSave;
    @BindView(R.id.tv_new_question)
    TextView tvQuestion;
    @BindView(R.id.tv_new_answer)
    TextView tvAnswer;
    @BindView(R.id.ad_view)
    AdView adView;

    private QuestionViewModel mQuestionViewModel;

    // Constructor, empty
    public QuestionNewQuestion() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_new_question_layout, container, false);
        Log.d(LOGTAG, "onCreateView");

        // ButterKnife
        ButterKnife.bind(this, view);

        // AdMob
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);


        mQuestionViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(QuestionViewModel.class);
        mQuestionViewModel.getCurrentCategory().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String submitQuestion = String.valueOf(tvQuestion.getText());
                        String submitAnswer = String.valueOf(tvAnswer.getText());

                        Log.d(LOGTAG, "current Category " + s);
                        Log.d(LOGTAG, "Entered Question: " + tvQuestion.getText());
                        Log.d(LOGTAG, "Entered Answer: " + tvAnswer.getText());

                        if (TextUtils.isEmpty(tvQuestion.getText())) {
                            Toast.makeText(getActivity(), "Question left blank", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(tvAnswer.getText())) {
                            Toast.makeText(getActivity(), "Answer left blank", Toast.LENGTH_SHORT).show();
                        } else {
                            Question question = new Question(submitQuestion, submitAnswer, s, 1, 0);
                            mQuestionViewModel.insert(question);

                            // Pop the last fragment transition from the manager's fragment back stack
                            // If there is nothing to pop the action will no be performed.
                            Objects.requireNonNull(getFragmentManager()).popBackStack();
                        }

                    }
                });
            }
        });
        return view;
    }
}
