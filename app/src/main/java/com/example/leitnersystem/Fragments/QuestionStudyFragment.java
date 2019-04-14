package com.example.leitnersystem.Fragments;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leitnersystem.Activities.QuestionActivity;
import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.Question;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionStudyFragment extends Fragment {

    private String LOGTAG = "QuestionStudyFragment";

    private int mId;
    private String mQuestion;
    private String mAnswer;
    private String mCategory;
    private int mBox;
    private int mCounter;
    private int mSize;

    private QuestionViewModel mQuestionViewModel;

    private int mCurrentQuestion;


    @BindView(R.id.tv_study_question)
    TextView tvStudyQuestion;
    @BindView(R.id.tv_study_answer)
    TextView tvStudyAnswer;
    @BindView(R.id.btn_study_correct)
    Button btnCorrect;
    @BindView(R.id.btn_study_wrong)
    Button btnWrong;
    @BindView(R.id.card_answer)
    CardView cardAnswer;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle onSavedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_study_layout, viewGroup, false);
        Log.d(LOGTAG, "onCreateView");

        mCurrentQuestion = 0;

        //ButterKnife
        ButterKnife.bind(this, view);

        // View Model
        mQuestionViewModel = ViewModelProviders.of(getActivity()).get(QuestionViewModel.class);

        final String category = mQuestionViewModel.getTextText();
        Log.d(LOGTAG, "Shawn " + category);
        mQuestionViewModel.findCategory(category).observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                mSize = questions.size();
                Log.d(LOGTAG, "Question " + mCurrentQuestion + "/" + mSize);

                if (mCurrentQuestion > mSize) {
                    btnCorrect.setVisibility(View.GONE);
                    mId= -1;
                } else {

                    mId = questions.get(mCurrentQuestion).getId();
                    mQuestion = questions.get(mCurrentQuestion).getQuestion();
                    mAnswer = questions.get(mCurrentQuestion).getAnswer();
                    mCategory = questions.get(mCurrentQuestion).getCategory();
                    mBox = questions.get(mCurrentQuestion).getBox();
                    mCounter = questions.get(mCurrentQuestion).getCounter();


                    mQuestionViewModel.setSize(String.valueOf(mSize));


                    Log.d(LOGTAG,
                            "Question: " + (mCurrentQuestion + 1) + "/" + mSize +
                                    " ID: " + String.valueOf(mId) +
                                    " Question: " + mQuestion +
                                    " Answer: " + mAnswer +
                                    " Category: " + mCategory +
                                    " Box: " + mBox +
                                    " Counter: " + mCounter);

                }
            }
        });


        /**
         * User presses this button if they answer the question correctly
         */
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Question question = new Question(mQuestion, "asnwer tell", "scifi", (mBox + 1), mCounter * 2);
                    question.setId(mId);
                    mQuestionViewModel.updateQuestion(question);
                    mCurrentQuestion++;


            }
        });

        /**
         * User presses this button if they answer question wrong.
         */
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "btnWrong pressed");

            }
        });

        cardAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "answer Card pressed");
            }
        });


        return view;
    }

//    private void QuestionLoad() {
//
//
//
//        mQuestionViewModel.getCurrentCategory().observe(getActivity(), new Observer<String>() {
//
//            @Override
//            public void onChanged(@Nullable String s) {
//                final String mCurrentCategory = String.valueOf(s);
//                Log.d(LOGTAG, "Category " + mCurrentCategory);
//
//                mQuestionViewModel.findCategory(mCurrentCategory).observe(getActivity(), new Observer<List<Question>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Question> questions) {
//
//
//                        if (mCurrentQuestion < questions.size()) {
//                            int eId = questions.get(mCurrentQuestion).getId();
//                            String eQuestion = questions.get(mCurrentQuestion).getQuestion();
//                            String eAnswer = questions.get(mCurrentQuestion).getAnswer();
//                            String eCat = questions.get(mCurrentQuestion).getCategory();
//                            int eBox = questions.get(mCurrentQuestion).getBox();
//                            int eCounter = questions.get(mCurrentQuestion).getCounter();
//
//
//                            Log.d(LOGTAG,
//                                    "Question: " + (mCurrentQuestion + 1) + "/" + questions.size() +
//                                            " ID: " + String.valueOf(eId) +
//                                            " Question: " + eQuestion +
//                                            " Answer: " + eAnswer +
//                                            " Category: " + eCat +
//                                            " Box: " + eBox +
//                                            " Counter: " + eCounter);
//
//                            tvStudyQuestion.setText(eQuestion);
//                            tvStudyAnswer.setText(eAnswer);
//                        }
//                    }
//                });
//            }
//        });
//    }


//    private void QuestionInfo() {
//        Log.d(LOGTAG, "QuestionInfo");
//
//        mQuestionViewModel.getCurrentCategory().observe(getActivity(), new Observer<String>() {
//
//            @Override
//            public void onChanged(@Nullable String s) {
//                final String mCurrentCategory = String.valueOf(s);
//
//                Log.d(LOGTAG, "Category " + mCurrentCategory);
//
//                mQuestionViewModel.findCategory(mCurrentCategory).observe(getActivity(), new Observer<List<Question>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Question> questions) {
//
//
//                        if(mCurrentQuestion < questions.size()) {
//                            int eId = questions.get(mCurrentQuestion).getId();
//                            String eQuestion = questions.get(mCurrentQuestion).getQuestion();
//                            String eAnswer = questions.get(mCurrentQuestion).getAnswer();
//                            String eCat = questions.get(mCurrentQuestion).getCategory();
//                            int eBox = questions.get(mCurrentQuestion).getBox();
//                            int eCounter = questions.get(mCurrentQuestion).getCounter();
//
//
//                            Log.d(LOGTAG,
//                                    "Question: " + (mCurrentQuestion  + 1) + "/" + questions.size() +
//                                    " ID: " + String.valueOf(eId) +
//                                    " Question: " +  eQuestion+
//                                    " Answer: " + eAnswer +
//                                    " Category: " + eCat +
//                                    " Box: " + eBox  +
//                                    " Counter: " + eCounter);
//
//                            tvStudyQuestion.setText(eQuestion);
//                            tvStudyAnswer.setText(eAnswer);
//
//                            Question question = new Question(eQuestion, eAnswer, eCat, eBox, eCounter);
//                            question.setId(eId);
//
//                            Log.d(LOGTAG, "Questions " + question);
//
//                            mQuestionViewModel.updateQuestion(question);
//
//
//                        } else {
//                            Log.d(LOGTAG, "No More questions");
//                            Toast.makeText(getActivity(), "No More Questions", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//            }
//        });
//    }
}
