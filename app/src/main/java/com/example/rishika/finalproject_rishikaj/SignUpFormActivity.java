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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFormActivity extends AppCompatActivity {
    FirebaseDatabase pdatabase;
    DatabaseReference profileRef;
    DatabaseReference postsRef;


    private FirebaseAuth pAuth;
    private FirebaseAuth.AuthStateListener pAuthListener;
    private String userUid;
    private String TAG = "SignUpFormActivity";


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_up_form);

        pdatabase = FirebaseDatabase.getInstance ();
        pAuth = FirebaseAuth.getInstance ();
        profileRef = pdatabase.getReference("profile"+"/"+pAuth.getCurrentUser ().getUid ());

        pAuthListener = new FirebaseAuth.AuthStateListener () {
            @Override
            public void onAuthStateChanged (@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser ();
                if (user != null) {
                    // User is signed in
                    Log.d (TAG, "onAuthStateChanged:signed_in:" + user.getUid ());
                    userUid = user.getUid ();
                } else {
                    // User is signed out
                    Log.d (TAG, "onAuthStateChanged:signed_out");
                    startActivity (new Intent (SignUpFormActivity.this, ProfileActivity.class));
                    finish ();
                }
            }
        };

    }


    @Override
    public void onStart () {
        super.onStart ();
        pAuth.addAuthStateListener (pAuthListener);
    }

    @Override
    public void onStop () {
        super.onStop ();
        if (pAuthListener != null) {
            pAuth.removeAuthStateListener (pAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu_main, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle item selection
        switch (item.getItemId ()) {
            default:
                return super.onOptionsItemSelected (item);
        }
    }

    public void saveProfile (View view) {

        EditText name = (EditText) findViewById (R.id.name);
        EditText age = (EditText) findViewById (R.id.age);
        StringBuilder diagnoses = new StringBuilder ();

        String Name;
        int Age;
        String Diagnoses;

        Name = name.getText ().toString ();
        Age = Integer.parseInt ((age.getText ().toString ()));


        String Relationship;
//        int parentBox = 1000;//first radio button id
//        int siblingBox = 1001;//second radio button id
//        int childBox = 1002;
//        int partnerBox = 1003;

        String Gender;
//        int femaleBox = 2000;
//        int maleBox = 2001;
//        int nonBinaryBox = 2002;
//        int transgenderBox = 2003;
//        int other = 2004;
//
        String StageOfRecovery;
////        int untreatedBox = 3000;
////        int inRecoveryBox = 3001;
////        int inTreatmentBox = 3002;
////        int relapsingBox = 3003;
//
        final CheckBox anorexia = (CheckBox) findViewById (R.id.anorexia);
        if (anorexia.isChecked ()) {
            diagnoses.append ("\n@string/anorexia");
        }
//
        final CheckBox bulimia = (CheckBox) findViewById (R.id.bulimia);
        if (bulimia.isChecked ()) {
            diagnoses.append ("\n@string/bulimia");
        }

        final CheckBox bingeEating = (CheckBox) findViewById (R.id.binge_eating);
        if (bingeEating.isChecked ()) {
            diagnoses.append ("\n@string/binge_eating");
            // bingeEating.setChecked(false);
        }

        final CheckBox ednos = (CheckBox) findViewById (R.id.ednos);
        if (ednos.isChecked ()) {
            //ednos.setChecked(false);
            diagnoses.append ("\n@string/ednos");
        }

        RadioGroup relationship_group = (RadioGroup) findViewById (R.id.relationship_group);
        RadioButton parent = (RadioButton) findViewById (R.id.parent);
        int parentbox = parent.getId ();

        RadioButton sibling = (RadioButton) findViewById (R.id.sibling);
        int siblingbox = sibling.getId ();

        RadioButton child = (RadioButton) findViewById (R.id.child);
        int childbox = child.getId ();

        RadioButton partner = (RadioButton) findViewById (R.id.partner);
        int partnerbox = partner.getId ();
        //parent.setId(parentBox);
////set an id
        int checkedButtonRelationship = relationship_group.getCheckedRadioButtonId ();
        switch (checkedButtonRelationship) {
            case R.id.parent:
                Relationship = "Parent";
                break;
            case R.id.sibling:
                Relationship = "Sibling";
                break;
            case R.id.child:
                Relationship = "Child";
                break;
            case R.id.partner:
                Relationship = "Partner";
                break;
            default:
                Relationship = "Undefined";
        }
//
        //View checkedButtonRelationshipID = relationship.findViewById(checkedButtonRelationship);
        RadioGroup gender = (RadioGroup) findViewById (R.id.gender_group);

        int checkedButtonGender = gender.getCheckedRadioButtonId ();
        switch (checkedButtonGender) {
            case R.id.female:
                Gender = "Female";
                break;
            case R.id.male:
                Gender = "Male";
                break;
            case R.id.non_binary:
                Gender = "Non Binary";
                break;
            case R.id.transgender:
                Gender = "Transgender";
                break;
            case R.id.other:
                Gender = "Other";
                break;
            default:
                Gender = "Undefined";
        }
        RadioGroup stageOfRecovery = (RadioGroup) findViewById (R.id.stage_group);

        int checkedButtonStageOfRecovery = stageOfRecovery.getCheckedRadioButtonId ();
        switch (checkedButtonStageOfRecovery) {
            case R.id.relapsing:
                StageOfRecovery = "Relapsing";
                break;
            case R.id.in_recovery:
                StageOfRecovery = "In recovery";
                break;
            case R.id.in_treatment:
                StageOfRecovery = "In treatment";
                break;
            case R.id.untreated:
                StageOfRecovery = "Never been treated";
                break;
            default:
                StageOfRecovery = "Unspecified";
        }

////        write ("Name", Name);
////        write ("age", Age);
////        write ("gender", Gender);
//
        Profile profile = new Profile (Name, Relationship, Age, Gender, StageOfRecovery);
//
        profileRef.setValue (profile);
////
////        postsRef.setValue (profile);
////
////        profileRef.push ().setValue (profile);
////
////        pdatabase.getReference (userUid).setValue (profile);

        Intent intent=new Intent(this, ProfileActivity.class);
//        intent.putExtra("profile",profile);
        startActivity (intent);



    }
    //push

//    private void write(profile) {
//        pDatabase.getReference(userUid).child(key).setValue(value);
//    }
}
