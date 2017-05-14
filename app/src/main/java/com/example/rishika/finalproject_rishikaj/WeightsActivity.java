package com.example.rishika.finalproject_rishikaj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class WeightsActivity extends AppCompatActivity {

    FirebaseDatabase wdatabase;
    DatabaseReference weightsRef;
   // private static final int NUM_LIST_ITEMS = 50;

    ArrayList<WeightsItem> weightsItems;

    private RecyclerView wItemsList;
    private RecyclerView.Adapter wAdapter;
    private RecyclerView.LayoutManager wLayoutManager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_weights);

        wdatabase = FirebaseDatabase.getInstance ();
        weightsRef = wdatabase.getReference ("weights");
        weightsItems = new ArrayList<> ();
//        weightsItems.add(new WeightsItem ("a","b"));
//        weightsItems.add(new WeightsItem ("c","d"));
//        weightsItems.add (new WeightsItem ("e","f"));


        wItemsList = (RecyclerView) findViewById (R.id.rv_weights);

        wLayoutManager = new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL, false);
        //wLayoutManager.canScrollHorizontally() = true;

        wItemsList.setLayoutManager (wLayoutManager);

        wItemsList.setHasFixedSize (true);

        wAdapter = new WeightsAdapter (weightsItems, this);

        wItemsList.setAdapter (wAdapter);

        weightsRef.addChildEventListener (new ChildEventListener () {

            @Override
            public void onChildAdded (DataSnapshot dataSnapshot, String s) {

                WeightsItem weightsItem = dataSnapshot.getValue (WeightsItem.class);
                int one =weightsItems.size ();
                //matching here for challenge.
                weightsItems.add(weightsItem);

                wAdapter.notifyDataSetChanged ();
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


//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            getMenuInflater().inflate(R.menu.main, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//
//            int itemId = item.getItemId();
//
//            switch (itemId) {
//            /*
//             * When you click the reset menu item, we want to start all over
//             * and display the pretty gradient again. There are a few similar
//             * ways of doing this, with this one being the simplest of those
//             * ways. (in our humble opinion)
//             */
//                case R.id.action_refresh:
//                    // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
//                    wAdapter = new WeightsAdapter(NUM_LIST_ITEMS, this);
//                    wNumbersList.setAdapter(wAdapter);
//                    return true;
//            }
//
//            return super.onOptionsItemSelected(item);
//        }

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu_nutrition, menu);
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
            case R.id.profile_menu:
                Intent intentP = new Intent (this, ProfileActivity.class);
                startActivity (intentP);
                return true;
            default:
                return super.onOptionsItemSelected (item);
        }

    }
}
