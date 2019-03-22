package com.example.leitnersystem.Adapters;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leitnersystem.Activities.DetailsActivity;
import com.example.leitnersystem.R;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {

    // Categories list
    public List<String> mData;
    // LayoutInflater
    public LayoutInflater mInflater;


    /**
     * ViewHolder, describes an item view and the metadata about its place in the RecyclerView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder  {
        // Layout Resource
        TextView myTextView;
        // Layout Resource, onClickListener will target the cardView.
        CardView cardView;

        // Constructor
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.rv_tx_category);
            cardView = itemView.findViewById(R.id.card_container);
        }
    }


    // Constructor
    public CategoryAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

    }

    /**
     * OnCreateViewHolder, call ed when RecyclerView needs a new View that can represent the items of
     * the given type.
     *
     * @param parent The ViewGroup into which the new view will be added after it is bound to an
     *              adapter position.
     *
     * @param viewType The view type of the new View.
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View view = mInflater.inflate(R.layout.rv_row_category, parent, false);

        return new ViewHolder(view);
    }

    /**
     * onBindViewHolder, called by the RecyclerView to display data at the specified position. This
     * method should update the contents of the itemView to reflect the item at the given position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at
     *               the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String category = mData.get(position);
        holder.myTextView.setText(category);

        Log.d("Shawn", "onBindViewHolder called " + position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Shawn", "Position clicked " +  position);

                // Get context returns the context the view is currently running in.
                Intent myIntent = new Intent (v.getContext(), DetailsActivity.class);
                v.getContext().startActivity(myIntent);

            }
        });
    }

    /**
     * getItem Count, gets the total item count.
     * @return returns item count.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
