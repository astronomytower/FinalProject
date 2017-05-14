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
 * Created by Rishika on 5/13/17.
 */

public class ChallengesAdapter extends RecyclerView.Adapter<ChallengesAdapter.ChallengesViewHolder> {
        Context cContext;
private static int viewHolderCount;

private ArrayList<Challenge> challengesList;
    public ChallengesAdapter(ArrayList<Challenge> challengesList, Context context) {
        cContext = context;
        this.challengesList = challengesList;
    }

    @Override
    public ChallengesAdapter. ChallengesViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {

        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.challenge_list_item, parent, false);
        // set the view's size, margins, padding and layout parameters

        ChallengesViewHolder ch = new ChallengesViewHolder(v);
        return ch;
    }

    public static class ChallengesViewHolder extends RecyclerView.ViewHolder  {

        public final TextView cDataTextView;
        //  private View itemView;

        public ChallengesViewHolder(View view) {
            super(view);
            cDataTextView = (TextView) view.findViewById(R.id.tv_item_challenge);
        }

    }

    @Override
    public void onBindViewHolder(ChallengesViewHolder ChallengesViewHolder, int position) {
        ChallengesViewHolder.cDataTextView.setText (challengesList.get(position).getTitle ()+
                "\n"+challengesList.get(position).getDate ()+"\n"+
                challengesList.get(position).getBody ());
    }

    @Override
    public int getItemCount() {
        return challengesList.size ();
    }
}
