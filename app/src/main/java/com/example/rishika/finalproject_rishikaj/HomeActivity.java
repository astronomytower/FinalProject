package com.example.rishika.finalproject_rishikaj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }



    public void openNutrition(View view){
        Intent intent =  new Intent(HomeActivity.this, NutritionActivity.class);
        startActivity(intent);
    }

    public void openRelapsePrevention(View view){
        Intent intent =  new Intent(HomeActivity.this, RelapsePreventionActivity.class);
        startActivity(intent);
    }

    public void openDailyChallenge(View view){
        Intent intent =  new Intent(HomeActivity.this, DailyChallengeActivity.class);
        startActivity(intent);
    }

    public void openSkills(View view){
        Intent intent =  new Intent(HomeActivity.this, SkillsActivity.class);
        startActivity(intent);
    }
}
