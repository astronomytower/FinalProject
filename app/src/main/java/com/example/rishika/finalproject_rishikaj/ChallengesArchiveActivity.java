package com.example.rishika.finalproject_rishikaj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChallengesArchiveActivity extends AppCompatActivity {

    //like ListActivity but clickable
    FirebaseDatabase cdatabase;
    DatabaseReference challengesRef;
    ArrayList<Challenge> challengesList;

    // private static final int NUM_LIST_ITEMS = 50;
    private RecyclerView cItemsList;
    private RecyclerView.Adapter cAdapter;
    private RecyclerView.LayoutManager cLayoutManager;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges_archive);


            cdatabase = FirebaseDatabase.getInstance ();
            challengesRef = cdatabase.getReference ("challenges");
            challengesList = new ArrayList<> ();

            cItemsList = (RecyclerView) findViewById (R.id.rv_challenges);
            cLayoutManager = new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL, false);
            //cLayoutManager.canScrollHorizontally() = true;

            cItemsList.setLayoutManager (cLayoutManager);

            cItemsList.setHasFixedSize (true);

            cAdapter = new ChallengesAdapter (challengesList, this);

            cItemsList.setAdapter (cAdapter);

            challengesRef.addChildEventListener (new ChildEventListener () {

                @Override
                public void onChildAdded (DataSnapshot dataSnapshot, String s) {

                    Challenge challenge = dataSnapshot.getValue (Challenge.class);
                    int one =challengesList.size ();
                    //matching here for challenge.

                    challengesList.add(challenge);

                    cAdapter.notifyDataSetChanged ();
                }

                @Override
                public void onChildChanged (DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved (DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved (DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled (DatabaseError databaseError) {

                }
            });

        }
}
