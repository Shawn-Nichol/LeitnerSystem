package com.example.leitnersystem.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.RoomQuestion.Question;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;

import java.util.Collections;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    String LOGTAG = "QuestionAdapter";

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

    public QuestionAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setQuestion(List<Question> question) {
        mQuestion =  question;
        notifyDataSetChanged();
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


    }

    @Override
    public int getItemCount() {
        Log.d(LOGTAG, "getItemCountSize " + mQuestion.size());
        return mQuestion.size();

    }
}
