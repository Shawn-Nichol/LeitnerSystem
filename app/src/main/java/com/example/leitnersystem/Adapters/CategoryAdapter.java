package com.example.leitnersystem.Adapters;

import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leitnersystem.Activities.DetailsActivity;
import com.example.leitnersystem.Fragments.CategoryFragment;
import com.example.leitnersystem.R;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {

    // Categories list
    private final List<String> mData;
    // LayoutInflater
    private final LayoutInflater mInflater;


    /**
     * ViewHolder, describes an item view and the metadata about its place in the RecyclerView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder  {
        // Layout Resource
        final TextView myTextView;
        // Layout Resource, onClickListener will target the cardView.
        final CardView cardView;

        // Constructor
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.rv_tx_category);
            cardView = itemView.findViewById(R.id.card_container_category);
        }
    }


    // Constructor
    public CategoryAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

    }

    /**
     * OnCreateViewHolder, called when RecyclerView needs a new View that can represent the items of
     * the given type.
     *
     * @param parent The ViewGroup into which the new view will be added after it is bound to an
     *              adapter position.
     *
     * @param viewType The view type of the new View.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {
        View view = mInflater.inflate(R.layout.rv_row_category, parent, false);

        return new ViewHolder(view);
    }

    /**
     * onBindViewHolder, called by the RecyclerView to display data at the specified position. This
     * method will update the contents of the itemView to reflect the item at the given position.
     *
     * @param holder The ViewHolder will be updated to represent the contents of the item at
     *               the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String category = mData.get(position);
        final int rvPosition = holder.getAdapterPosition();
        holder.myTextView.setText(category);


        Log.d("Shawn", "onBindViewHolder called " + position);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Shawn", "Position clicked " +  rvPosition);

                // Get context returns the context the view is currently running in.
                Intent myIntent = new Intent (v.getContext(), DetailsActivity.class);
                // Category Title
                myIntent.putExtra("Category", mData.get(position));
                v.getContext().startActivity(myIntent);

            }
        });
    }

    /**
     * getItemCount, gets the total item count for the RecyclerView.
     * @return returns item count.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
