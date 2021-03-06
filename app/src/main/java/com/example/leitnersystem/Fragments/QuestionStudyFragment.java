package com.example.leitnersystem.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class QuestionStudyFragment extends Fragment {

    private final String LOGTAG = "QuestionStudyFragment";

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
    @BindView(R.id.card_question)
    CardView cardQuestion;
    @BindView(R.id.ad_view)
    AdView adView;

    // Constructor, empty
    public QuestionStudyFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle onSavedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_question_study_layout, viewGroup, false);
        Log.d(LOGTAG, "onCreateView");

        mCurrentQuestion = 0;


        //ButterKnife
        ButterKnife.bind(this, view);

        // AdMob
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        // View Model
        mQuestionViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(QuestionViewModel.class);

        // Fragment Manager
        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();


        final String savedCategory = mQuestionViewModel.getTextText();
        Log.d(LOGTAG, "Saved Category " + savedCategory);
        mQuestionViewModel.findCategory(savedCategory).observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                mSize = Objects.requireNonNull(questions).size();

                // When mCurrentQuestions is the same size as the list of question remove the option
                // to ask for the next question.
                Log.d(LOGTAG, "mCurrentQuestion = " + mCurrentQuestion + " mSize " + (mSize - 1));

                if ((mCurrentQuestion) > (mSize - 1) || mSize == 0) {
                    Log.d(LOGTAG, "No more questions");

                    QuestionStudyResultsFragment questionStudyResultsFragment = new QuestionStudyResultsFragment();

                    fragmentTransaction
                            .replace(R.id.activity_question_container, questionStudyResultsFragment, "questionStudyResultsFragment")
                            .commit();


                } else {
                    mCounter = questions.get(mCurrentQuestion).getCounter();
                    mId = questions.get(mCurrentQuestion).getId();
                    mQuestion = questions.get(mCurrentQuestion).getQuestion();
                    mAnswer = questions.get(mCurrentQuestion).getAnswer();
                    mCategory = questions.get(mCurrentQuestion).getCategory();
                    mBox = questions.get(mCurrentQuestion).getBox();
                    // Only asks questions if counter equals 0.
                    if (mCounter == 0) {
                        mQuestionViewModel.setSize(String.valueOf(mSize));

                        tvStudyQuestion.setText(mQuestion);

                        Log.d(LOGTAG,
                                "Question observer: " + (mCurrentQuestion + 1) + "/" + mSize +
                                        " ID: " + String.valueOf(mId) +
                                        " Question: " + mQuestion +
                                        " Answer: " + mAnswer +
                                        " Category: " + mCategory +
                                        " Box: " + mBox +
                                        " Counter: " + mCounter);
                    } else {

                        Log.d(LOGTAG, "Question " + mQuestion + " Skipped. Counter = " + mCounter);
                        Question question = new Question(mQuestion, mAnswer, mCategory, (mBox), (mCounter - 1));
                        question.setId(mId);
                        mQuestionViewModel.updateQuestion(question);

                        mCurrentQuestion++;

                    }
                }
            }
        });


        // User presses this button if they answer the question correctly
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int counter = 0;
                int box = mBox + 1;

                switch (box) {
                    case 1:
                        counter = 1;
                        break;
                    case 2:
                        counter = 2;
                        break;
                    case 3:
                        counter = 4;
                        break;
                    case 4:
                        counter = 8;
                        break;
                    case 5:
                        counter = -1;
                        break;
                    default:

                }

                Log.d(LOGTAG, "Button correct " +
                        " ID: " + String.valueOf(mId) +
                        " Question: " + mQuestion +
                        " Answer: " + mAnswer +
                        " Category: " + mCategory +
                        " Box: " + box +
                        " Counter: " + counter);


                Question question = new Question(mQuestion, mAnswer, mCategory, box, counter);
                question.setId(mId);
                mQuestionViewModel.updateQuestion(question);
                tvStudyAnswer.setText(R.string.answer_card);
                mCurrentQuestion++;

            }
        });


        //  User presses this button if they answer question wrong.

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "btnWrong pressed");
                int counter;

                if (mCounter <= 0) {
                    counter = 0;
                } else if (mCounter < 0) {
                    counter = mCounter;
                } else {
                    counter = mCounter - 1;
                }

                Log.d(LOGTAG, "Button Wrong" +
                        " ID: " + String.valueOf(mId) +
                        " Question: " + mQuestion +
                        " Answer: " + mAnswer +
                        " Category: " + mCategory +
                        " Box: " + 1 +
                        " Counter: " + counter);
                Question question = new Question(mQuestion, mAnswer, mCategory, 1, counter);
                question.setId(mId);
                mQuestionViewModel.updateQuestion(question);
                tvStudyAnswer.setText(R.string.answer_card);
                mCurrentQuestion++;

            }
        });

        cardAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "answer Card pressed");
                tvStudyAnswer.setText(mAnswer);
            }
        });


        return view;
    }

}
