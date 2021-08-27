package com.cth.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cth.myapplication.R;

public class PlanOptionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_options);
    }

    public void buttonCurrentSemesterOnClick(View view){
        PlanOptionsActivity.this.startActivity(new Intent(PlanOptionsActivity.this, CurrentSemesterActivity.class));
    }

    public void buttonCompletedSemestersOnClick(View view){
        PlanOptionsActivity.this.startActivity(new Intent(PlanOptionsActivity.this, CompletedSemestersActivity.class));
    }

    public void buttonUpcomingSemestersOnClick(View view){
        PlanOptionsActivity.this.startActivity(new Intent(PlanOptionsActivity.this, UpcomingSemestersActivity.class));
    }

    public void buttonManagePlanOnClick(View view){
        PlanOptionsActivity.this.startActivity(new Intent(PlanOptionsActivity.this, PlanListActivity.class));
    }
}
