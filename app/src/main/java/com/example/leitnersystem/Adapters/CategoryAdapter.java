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

import com.example.leitnersystem.Activities.QuestionActivity;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.R;

import java.util.Collections;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>  {

    /**
     * CategoryViewHolder, describes an item view and the metadata about its place in the RecyclerView.
     */
    public class CategoryViewHolder extends RecyclerView.ViewHolder  {
        // Layout Resource
        final TextView myTextView;
        // Layout Resource, onClickListener will target the cardView.
        final CardView cardView;

        // Constructor
        CategoryViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.rv_tx_category);
            cardView = itemView.findViewById(R.id.card_container_category);
        }
    }

    private final LayoutInflater mInflater;
    private List<Category> mCategories = Collections.emptyList();

    // Constructor
    public CategoryAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setTitles(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
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
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {
        View view = mInflater.inflate(R.layout.rv_row_category, parent, false);
        return new CategoryViewHolder(view);
    }

    /**
     * onBindViewHolder, called by the RecyclerView to display data at the specified position. This
     * method will update the contents of the itemView to reflect the item at the given position.
     *
     * @param holder The CategoryViewHolder will be updated to represent the contents of the item at
     *               the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Category current = mCategories.get(position);
        holder.myTextView.setText(current.getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Get Context returns the context the view is currently running in.
                Intent myIntent = new Intent(v.getContext(), QuestionActivity.class);
                // Category Category
                Log.d("Shawn", "Category Category " + current.getTitle());
                myIntent.putExtra("Category", current.getTitle());
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
        return mCategories.size();
    }
}
