package com.example.rishika.finalproject_rishikaj;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;


public class DailyChallengeActivity extends AppCompatActivity {

    FirebaseDatabase cdatabase;
    DatabaseReference challengesRef;
    ArrayList<Challenge> challengesList;

    private RecyclerView dcRecyclerView;
    private RecyclerView.Adapter dcAdapter;
    private RecyclerView.LayoutManager dcLayoutManager;


    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenge);
        challengesList = new ArrayList<Challenge>();

        cdatabase = FirebaseDatabase.getInstance ();
        challengesRef = cdatabase.getReference ("challenges");
        challengesList = new ArrayList<> ();

        dcRecyclerView = (RecyclerView) findViewById (R.id.rv_daily_challenge);

        dcLayoutManager = new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL, false);
        // cLayoutManager.canScrollHorizontally() = true;

        dcRecyclerView.setLayoutManager (dcLayoutManager);

        dcRecyclerView.setHasFixedSize (true);

        dcAdapter = new ChallengesAdapter (challengesList, this);

        dcRecyclerView.setAdapter (dcAdapter);

        Date localDate = new Date ();
        DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd");
        final String today = dateFormat.format(localDate);
//        Challenge c =new Challenge ("a","be",s);
//        final String today =c.toString ();

        //check on firebase directly and download only the relevant item from list

        challengesRef.addChildEventListener (new ChildEventListener () {

            @Override
            public void onChildAdded (DataSnapshot dataSnapshot, String s) {

                Challenge challenge = dataSnapshot.getValue (Challenge.class);
                int one =challengesList.size ();
                //matching here for challenge.
                if (today.equals (challenge.date)){
                    challengesList.add(challenge);

                    dcAdapter.notifyDataSetChanged ();
                }
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
    public void openChallengeNote(View view){
        Intent intent =  new Intent(DailyChallengeActivity.this, ChallengeNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu_daily_challenge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle item selection
        switch (item.getItemId ()) {
            case R.id.home_menu:
                Intent intentH = new Intent (this, MainActivity.class);
                startActivity (intentH);
                return true;
            case R.id.archive_menu:
                Intent intentA = new Intent (this, ChallengesArchiveActivity.class);
                startActivity (intentA);
                return true;
            case R.id.note_menu:
                Intent intentN = new Intent (this, ChallengeNoteActivity.class);
                startActivity (intentN);
                return true;
            default:
                return super.onOptionsItemSelected (item);
        }

    }
}
