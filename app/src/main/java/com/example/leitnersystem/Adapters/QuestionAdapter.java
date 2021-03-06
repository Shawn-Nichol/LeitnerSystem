package com.example.leitnersystem.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leitnersystem.Fragments.QuestionDetailFragment;
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
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        final Question current = mQuestion.get(position);
        holder.myTextView.setText(current.getQuestion());
        final int mPosition = holder.getAdapterPosition();

        holder.cardView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "RecyclerView Question number selected " + (mPosition + 1));

                Bundle arguments = new Bundle();
                arguments.putInt("Key_QuestionNumber", mPosition);

                QuestionDetailFragment questionDetailFragment = new QuestionDetailFragment();

                questionDetailFragment.setArguments(arguments);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();



                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_question_container, questionDetailFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mQuestion.size();

    }
}
