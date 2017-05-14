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
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

//login screen
public class MainActivity extends AppCompatActivity {

    private String mUsername;

    private static final String TAG = "MainActivity";
    public static final String ANONYMOUS = "anonymous";

    public static final int RC_SIGN_IN = 1;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        mUsername = ANONYMOUS;
        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance ();
        mFirebaseAuth = FirebaseAuth.getInstance ();


        mAuthStateListener = new FirebaseAuth.AuthStateListener () {

            List<AuthUI.IdpConfig> providers = Arrays.asList (
                    new AuthUI.IdpConfig.Builder (AuthUI.EMAIL_PROVIDER).build (),
                    new AuthUI.IdpConfig.Builder (AuthUI.GOOGLE_PROVIDER).build ()
            );

            @Override
            public void onAuthStateChanged (@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser ();
                if (user != null) {
                    // User is signed in
                    Toast.makeText (MainActivity.this, "You're now signed in.", Toast.LENGTH_SHORT).show ();
                  //  onSignedInInitialize (user.getDisplayName ());
                    onSignedInInitialize (user.getUid());

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    mUsername = user.getUid();
                  //  displayInfo();

                } else {
                    // User is signed out
                    onSignedOutCleanup ();
                    startActivityForResult (
                            AuthUI.getInstance ()
                                    .createSignInIntentBuilder ()
                                    .setIsSmartLockEnabled (!BuildConfig.DEBUG)
                                    .setProviders (providers)
                                    .build (),
                            RC_SIGN_IN);
                }
            }
        };

    }

//    public void openHome(View view) {
//        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//        startActivity(intent);
//    }

    public void openNutrition (View view) {
        Intent intent = new Intent (MainActivity.this, NutritionActivity.class);
        startActivity (intent);
    }

    public void openRelapsePrevention (View view) {
        Intent intent = new Intent (MainActivity.this, RelapsePreventionActivity.class);
        startActivity (intent);
    }

    public void openDailyChallenge (View view) {
        Intent intent = new Intent (MainActivity.this, DailyChallengeActivity.class);
        startActivity (intent);
    }

    public void openSkills (View view) {
        Intent intent = new Intent (MainActivity.this, SkillsActivity.class);
        startActivity (intent);
    }

    public void openProfile (View view) {
        Intent intent = new Intent (MainActivity.this, ProfileActivity.class);
        startActivity (intent);
    }

    public void signOut (View view) {
        AuthUI.getInstance ().signOut (this);
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText (this, "Signed in!", Toast.LENGTH_SHORT).show ();
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText (this, "Sign in canceled", Toast.LENGTH_SHORT).show ();
                finish ();
            }
        }
    }

    @Override
    protected void onResume () {
        super.onResume ();
        mFirebaseAuth.addAuthStateListener (mAuthStateListener);
    }

    @Override
    protected void onPause () {
        super.onPause ();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener (mAuthStateListener);
        }
//detach eventlistener
        //clear adapter
    }

    private void onSignedInInitialize (String username) {
        mUsername = username;
        //attachDatabaseReadListener();
    }

    private void onSignedOutCleanup () {
        mUsername = ANONYMOUS;
        //mMessageAdapter.clear();
        //detachDatabaseReadListener();
    }

    @Override
    public void onStart () {
        super.onStart ();
        mFirebaseAuth.addAuthStateListener (mAuthStateListener);
    }

    @Override
    public void onStop () {
        super.onStop ();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener (mAuthStateListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle item selection
        switch (item.getItemId ()) {
            case R.id.sign_out_menu:
                mFirebaseAuth.signOut ();
                AuthUI.getInstance ().signOut (this);
                return true;
            default:
                return super.onOptionsItemSelected (item);
        }

    }
}

//    public void signOut(View v) {
//        if (v.getId() == R.id.sign_out) {
//            AuthUI.getInstance()
//                    .signOut(this)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        public void onComplete(@NonNull Task<Void> task) {
//                            // user is now signed out
//                            startActivity(new Intent(MyActivity.this, SignInActivity.class));
//                            finish();
//                        }
//                    });
//        }
//    }






