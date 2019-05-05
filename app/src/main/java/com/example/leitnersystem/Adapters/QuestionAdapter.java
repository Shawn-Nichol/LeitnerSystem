package com.example.leitnersystem.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leitnersystem.Fragments.QuestionDetailFragment;
import com.example.leitnersystem.Fragments.QuestionFragment;
import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.Question;


import java.util.Collections;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private final String LOGTAG = "QuestionAdapter";

    /**
     * QuestionViewHolder, describes an item view and the metadata about its place in the RecyclerView.
     */
    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        final TextView myTextView;
        final CardView cardView;

        // Constructor
        QuestionViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.rv_tx_question);
            cardView = itemView.findViewById(R.id.card_container_question);
        }
    }

    private final LayoutInflater mInflater;
    private List<Question> mQuestion = Collections.emptyList();
//    private QuestionViewModel mQuestionViewModel;

    public QuestionAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    // Used to identify the question the user wants to delete.
    public void setQuestion(List<Question> question) {
        mQuestion =  question;
        notifyDataSetChanged();
    }

    // Get Question to delete
    public Question getQuestionAt(int position) {
        return mQuestion.get(position);
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_row_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionViewHolder holder, final int position) {
        final Question current = mQuestion.get(position);
        holder.myTextView.setText(current.getQuestion());

        final int mPosition = holder.getAdapterPosition();

        holder.cardView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "RecyclerView Question number selected " + (mPosition));

                String textTransitionName = "trans_text_" + (mPosition);

                QuestionDetailFragment questionDetailFragment = new QuestionDetailFragment();
                QuestionFragment questionFragment = new QuestionFragment();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                // Inflate Transition
                Transition changeTransform = TransitionInflater.from(holder.cardView.getContext())
                        .inflateTransition(R.transition.question_transform);

                holder.cardView.setTransitionName(textTransitionName);
                Log.d(LOGTAG, "setTransitionName = " + holder.cardView.getTransitionName());


                // Exit Transition
                questionFragment.setSharedElementReturnTransition(changeTransform);

                // Enter Transition on QuestionDetailFragment
                questionDetailFragment.setSharedElementEnterTransition(changeTransform);

                // Bundle
                Bundle arguments = new Bundle();
                arguments.putInt("Key_QuestionNumber", mPosition);
                arguments.putString("Key_TRANS_TEXT", textTransitionName);
                Log.d(LOGTAG, "Key_QuestionNumber " + mPosition);
                Log.d(LOGTAG, "Key_TRANS_TEXT = " + textTransitionName);

                questionDetailFragment.setArguments(arguments);

                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_question_container, questionDetailFragment)
                        .addToBackStack(null)
                        .addSharedElement(holder.cardView, textTransitionName)
                        .commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mQuestion.size();

    }
}
