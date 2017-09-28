package com.apkglobal.tiffin7;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Acer on 12/09/2017.
 */


public class RecyclerViewAdapterFoodDetails extends RecyclerView.Adapter<RecyclerViewAdapterFoodDetails.ViewHolder> {


    Context homeContext;
    ModelFood food;


    ArrayList<ModelFood>    foodlist;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tv_b_name, tv_b_des, tv_b_dailyprice, tv_b_weeklyprice, tv_b_monthlyprice;
        View v;

        public ViewHolder(View v) {
            super(v);
            this.v = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapterFoodDetails(Context homeContext, ArrayList<ModelFood> foodlist) {

        this.foodlist = foodlist;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodlist, parent, false);
        ViewHolder holder = new ViewHolder(v);
        holder.tv_b_name = (TextView) v.findViewById(R.id.tv_b_name);
        holder.tv_b_des = (TextView) v.findViewById(R.id.tv_b_des);
        holder.tv_b_dailyprice = (TextView) v.findViewById(R.id.tv_b_dailyprice);
        holder.tv_b_weeklyprice = (TextView) v.findViewById(R.id.tv_b_weeklyprice);
        holder.tv_b_monthlyprice = (TextView) v.findViewById(R.id.tv_b_monthlyprice);

        Context homeContext = parent.getContext();
        // set the view's size, margins, paddings and layout parameters

        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tv_b_name.setText(foodlist.get(position).food_name);
        holder.tv_b_des.setText(foodlist.get(position).food_des);
        if (!(foodlist.get(position).food_des != null
                && !foodlist.get(position).food_des.isEmpty()
                && !foodlist.get(position).food_des.equals("null"))) {
            holder.tv_b_des.setText("");
        }
        holder.tv_b_dailyprice.setText(foodlist.get(position).food_dailyprice);
        holder.tv_b_weeklyprice.setText(foodlist.get(position).food_weeklyprice);
        holder.tv_b_monthlyprice.setText(foodlist.get(position).food_monthlyprice);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodlist.size();
    }
}