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

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionNewQuestion extends Fragment {

    String LOGTAG = "QuestionNewQuestion";
    @BindView (R.id.btn_question_save) Button btnSave;
    @BindView (R.id.tv_new_question) TextView tvQuestion;
    @BindView (R.id.tv_new_answer) TextView tvAnswer;

    private QuestionViewModel mQuestionViewModel;

    // Empty constructor
    public QuestionNewQuestion() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_question_new_question_layout, container, false);

        // ButterKnife
        ButterKnife.bind(this, view);



        mQuestionViewModel = ViewModelProviders.of(getActivity()).get(QuestionViewModel.class);


        mQuestionViewModel.getCurrentCategory().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {

                btnSave.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        String submitQuestion = String.valueOf(tvQuestion.getText());
                        String submitAnswer = String.valueOf(tvAnswer.getText());

                        Log.d(LOGTAG, "current Category " + s);
                        Log.d(LOGTAG, "Entered Question: " + tvQuestion.getText());
                        Log.d(LOGTAG, "Entered Answer: " + tvAnswer.getText());

                        if(TextUtils.isEmpty(tvQuestion.getText())) {
                           Toast.makeText(getActivity(), "Question left blank", Toast.LENGTH_SHORT).show();
                       } else if (TextUtils.isEmpty(tvAnswer.getText())) {
                           Toast.makeText(getActivity(), "Answer left blank", Toast.LENGTH_SHORT).show();
                       } else {
                            Question question = new Question(submitQuestion, submitAnswer, s,1, 1);
                            mQuestionViewModel.insert(question);

                            // Pop the last fragment transition from the manager's fragment back stack
                            // If there is nothing to pop the action will no be performed.
                            getFragmentManager().popBackStack();
                       }

                    }
                });
            }
        });
        return view;
    }
}
