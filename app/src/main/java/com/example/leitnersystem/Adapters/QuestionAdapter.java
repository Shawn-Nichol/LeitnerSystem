package com.example.leitnersystem.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

    public QuestionAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    /**
     *
     * @param question List of questions to choose from.
     */
    public void setQuestion(List<Question> question) {
        mQuestion =  question;
        notifyDataSetChanged();
    }

    /**
     * getQuestionAt is used to identify the question the user wants to delete.
     *
     * @param position the position selected in the list
     * @return the position selected.
     */
    public Question getQuestionAt(int position) {
        return mQuestion.get(position);
    }

    /**
     * OnCreateViewHolder is called when RecyclerView needs to add a new view that can represent the
     * items of the given type
     *
     * @param parent The ViewGroup into which the new view will be added after it is bound to an
     *               adapter position.
     * @param viewType The View type of the new view.
     */
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_row_question, parent, false);
        return new QuestionViewHolder(view);
    }

    /**
     * onBindViewHolder, called by the RecyclerView to display data at the specified position. This
     * method will update the contents of the ItemView to reflect the item at the given position.
     *
     * @param holder The QuestionView holder will be updated to represent the contents of the item at
     *               the given position.
     * @param position The position of the item with in the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull final QuestionViewHolder holder, final int position) {
        final Question current = mQuestion.get(position);
        holder.myTextView.setText(current.getQuestion());

        final int mPosition = holder.getAdapterPosition();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "RecyclerView Question number selected " + (mPosition));

                QuestionDetailFragment questionDetailFragment = new QuestionDetailFragment();
                QuestionFragment questionFragment = new QuestionFragment();

                String textTransitionName = "trans_text_" + (mPosition);

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
                questionDetailFragment.setArguments(arguments);

                Log.d(LOGTAG, "Key_QuestionNumber " + mPosition);
                Log.d(LOGTAG, "Key_TRANS_TEXT = " + textTransitionName);

                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_question_container, questionDetailFragment)
                        .addToBackStack(null)
                        .addSharedElement(holder.cardView, textTransitionName)
                        .commit();

            }
        });


    }

    /**
     * getItemCount, gets the total item count for the RecyclerView.
     *
     * @return item count.
     */
    @Override
    public int getItemCount() {
        return mQuestion.size();

    }
}
