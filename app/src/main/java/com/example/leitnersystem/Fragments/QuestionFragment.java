package com.example.leitnersystem.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.leitnersystem.Adapters.QuestionAdapter;
import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.Question;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionFragment extends Fragment {

    String LOGTAG = "QuestionFragment";

    private QuestionViewModel mQuestionViewModel;
    private String mCategory;

    @BindView(R.id.btn_study) Button btnStudy;

    // Fragment requires empty constructor.
    public QuestionFragment() {
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG, "onCreateView");

        // Inflate fragment_question_layout.
        View view = inflater.inflate(R.layout.fragment_question_layout, container, false);

        // ButterKnife
        ButterKnife.bind(this, view);

        // RecyclerView setup
        RecyclerView recyclerView = view .findViewById(R.id.rv_question);
        final QuestionAdapter adapter = new QuestionAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Fragment Manager Setup
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Floating action Button
        final FloatingActionButton fab = view.findViewById(R.id.fab_button);

        // Get new or existing ViewModel from the View Model provider
        mQuestionViewModel = ViewModelProviders.of(getActivity()).get(QuestionViewModel.class);
        mCategory = mQuestionViewModel.getTextText();

        mQuestionViewModel.findCategory(mCategory).observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                Log.d(LOGTAG, "ViewModel getCategory " + mCategory);
                Log.d(LOGTAG, "ViewModel questions + " + questions);

                adapter.setQuestion(questions);
            }
        });


        /**
         * Used to delete Question by swiping to the left or right.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mQuestionViewModel.delete(adapter.getQuestionAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Question deleted.", Toast.LENGTH_SHORT).show();
            }


        }).attachToRecyclerView(recyclerView);






        /**
         * Launches study fragment
         */
        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "btnStudy  pressed");
                QuestionStudyFragment questionStudyFragment = new QuestionStudyFragment();


//                mQuestionViewModel.
                //mQuestionViewModel.getCurrentCategory().observe(getActivity(), new Observer<String>() {
//                mQuestionViewModel.findCategory(mCategory).observe(getActivity(), new Observer<List<Question>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Question> questions) {
//                        int size = questions.size();
//                        int id;
//                        String mQuestion;
//                        String answer;
//                        String category;
//                        int box;
//                        int counter;
//
//                        Log.d(LOGTAG, "btnStudy question count " + size);
//
//
//
//                        for(int i = 0; i < size; i++) {
//
//                            id = questions.get(i).getId();
//                            mQuestion = questions.get(i).getQuestion();
//                            answer = questions.get(i).getAnswer();
//                            category = questions.get(i).getCategory();
//                            box = questions.get(i).getBox();
//                            counter = questions.get(i).getCounter();
//
//                            Question question = new Question(mQuestion, answer, category, box, (counter -1));
//                            question.setId(id);
//
//                            mQuestionViewModel.updateQuestion(question);
//                            mQuestionViewModel.findCategory(mCategory).removeObserver(questions);
//                        }
//                    }
//                });



                fragmentTransaction
                        .replace(R.id.activity_question_container, questionStudyFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        /**
         * FAB will launch QuestionNewQuestion fragment and allow the user to enter a question.
         */
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "FAB button press");

                QuestionNewQuestion newQuestion = new QuestionNewQuestion();

                // Start fragment transaction
                fragmentTransaction
                        .replace(R.id.activity_question_container, newQuestion)
                        .addToBackStack(null)
                        .commit();
            }
        });
       return view;
    }
}
