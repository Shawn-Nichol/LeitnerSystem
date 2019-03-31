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

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    // Something List
    private List<String> mData;
    // LayoutInflater
    private LayoutInflater mInflater;

    // Context, used to call string resource
    private Context mContext;


    /**
     * CategoryViewHolder inner class, Describes an item view and the metadata about its place in the RecyclerView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Layout Resources
        final TextView boxNum;
        final TextView dayNum;
        final TextView flashCards;
        final TextView fcCompleted;

        // Layout Resource, OnClickListener will target the cardView,
        // Might not need this will decide later
        final CardView cardview;

        ViewHolder(View itemView) {
            super(itemView);
            boxNum = itemView.findViewById(R.id.rv_row_details_tv_box_num);
            dayNum = itemView.findViewById(R.id.rv_row_details_tv_days);
            flashCards = itemView.findViewById(R.id.rv_row_details_tv_fc);
            fcCompleted = itemView.findViewById(R.id.rv_row_details_tv_fc_completed);
            cardview = itemView.findViewById(R.id.card_container_details);
        }
    }

    // Constructor
    public DetailsAdapter(Context context, List<String> data) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    /**
     * onCreateViewHolder, called when the recyclerView needs a that can represent the items of the
     * given type.
     *
     * @param parent The ViewGroup into which the new view will be added after it is bound to an
     *               adapter position.
     * @param viewType The view type of the new View.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_row_details, parent, false);
        return new ViewHolder(view);
    }


    /**
     * onBindViewHolder, call by the recyclerView to display data at the specified position. This
     * method will update the contents of the itemView to reflect teh contents of the item.
     *
     * @param holder This CategoryViewHolder will be updated to represent the contents of the item at the
     *          given position.
     * @param position The position of the item within the adapter data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("Shawn", "onBindViewHolder called " + position);

        String mBox = mContext.getString(R.string.details_box_num) + mData.get(position);
        String mBoxDay;



        switch(position) {
            case 0:
                mBoxDay = mContext.getString(R.string.details_day1);
                break;
            case 1:
                mBoxDay = mContext.getString(R.string.details_day2);
                break;
            case 2:
                mBoxDay = mContext.getString(R.string.details_day4);
                break;
            case 3:
                mBoxDay = mContext.getString(R.string.details_day8);
                break;
            case 4:
                mBoxDay = mContext.getString(R.string.details_day_retired);
                break;
            default:
                mBoxDay = mContext.getString(R.string.details_day_error);
                break;
        }

        holder.boxNum.setText(mBox);
        holder.dayNum.setText(mBoxDay);

    }

    /**
     * getItemCount, gets the total item count for the recyclerView.
     * @return returns item count
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
