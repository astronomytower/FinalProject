package com.example.rishika.finalproject_rishikaj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NutritionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
    }

public void openWeight(View view){
    Intent intent = new Intent (this, WeightsActivity.class);
    startActivity (intent);
}


}
