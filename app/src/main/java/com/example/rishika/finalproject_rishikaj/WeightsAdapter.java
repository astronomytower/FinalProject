package com.example.rishika.finalproject_rishikaj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rishika on 5/1/17.
 */

public class WeightsAdapter extends RecyclerView.Adapter<WeightsAdapter.WeightsViewHolder> {
    Context wContext;
    //final private ListItemClickListener wOnClickListener;
    private static int viewHolderCount;

    private ArrayList<WeightsItem> weightsInformation;
    //private final WeightsAdapterOnClickHandler wClickHandler;




    public WeightsAdapter(ArrayList<WeightsItem> weightsInformation, Context context) {
        wContext = context;
        this.weightsInformation = weightsInformation;
    }

//    public WeightsAdapter() {
//        wClickHandler = clickHandler;
//    }

    @Override
    public WeightsAdapter. WeightsViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weight_list_item, parent, false);
        // set the view's size, margins, padding and layout parameters

        WeightsViewHolder wh = new WeightsViewHolder(v);
        return wh;
    }

    public static class WeightsViewHolder extends RecyclerView.ViewHolder  {

        public final TextView wDataTextView;
        //  private View itemView;

        public WeightsViewHolder(View view) {
            super(view);
            wDataTextView = (TextView) view.findViewById(R.id.tv_item_weight);
        }

//
//        void bind(int listIndex){
//
//            listItemNumberView.setText(String.valueOf(listIndex));}

//        @Override
//        public void onClick(View v) {
//            int clickedPosition = getAdapterPosition();
//            String weightsData = weightsInformation[clickedPosition];
//            wClickHandler.onClick(weightsData);
//        }
    }

    @Override
    public void onBindViewHolder(WeightsViewHolder weightsViewholder, int position) {
      weightsViewholder.wDataTextView.setText (weightsInformation.get (position).getTitle ()+"\n"+
        weightsInformation.get (position).getBody ());
    }

    @Override
    public int getItemCount() {
        return weightsInformation.size ();
    }


}