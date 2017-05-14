package com.example.rishika.finalproject_rishikaj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    FirebaseDatabase pDatabase;
    DatabaseReference profileRef;
    private FirebaseAuth pAuth;
    private FirebaseAuth.AuthStateListener pAuthListener;
    private String userUid;
    private Profile profile;
    private String TAG = "ProfileActivity";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_profile_actvity);

        pDatabase = FirebaseDatabase.getInstance ();
        pAuth = FirebaseAuth.getInstance ();
        profileRef = pDatabase.getReference("profile"+"/"+pAuth.getCurrentUser ().getUid ());
        pAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    userUid = user.getUid();
                    displayInfo();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        pAuth.addAuthStateListener(pAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (pAuthListener != null) {
            pAuth.removeAuthStateListener(pAuthListener);
        }
    }

    public void editProfile(View view){
        Intent intent =  new Intent(ProfileActivity.this, SignUpFormActivity.class);
        startActivity(intent);
    }

    public void displayInfo() {
        TextView fName = (TextView) findViewById(R.id.name_field);
//        TextView fAge = (TextView) findViewById(R.id.age_field);
//        TextView fGender = (TextView) findViewById(R.id.gender_field);

        updateField(fName, "name");
//        updateField(fAge, "age");
//        updateField(fGender, "gender");
    }

    public void updateField(final TextView field, String key) {

      profileRef.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Profile value = dataSnapshot.getValue(Profile.class);
                Log.d(TAG, "Value is: " + value);
                if (value == null) field.setText("Not set");
                else field.setText(value.toString ());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void update() {
        startActivity(new Intent(this, SignUpFormActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

//create separate menu for just Home button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.home_menu:
                goHome ();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void goHome(){
        Intent intent = new Intent (ProfileActivity.this,MainActivity.class);
        startActivity (intent);
    }


}
