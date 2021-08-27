package com.cth.myapplication.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cth.myapplication.R;
import com.cth.myapplication.objects.Term;

public class SemesterSelectionActivity extends AppCompatActivity {
    private Spinner termSpinner;
    private ArrayAdapter termAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter yearAdapter;
    private int selectedTermPosition;
    private int selectedYear;
    private Button startButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_selection);
        termSpinnerController();
        yearSpinnerController();
        buttonChooseCourseOnClick();
    }//onCreate

    //Controller for term spinner
    private void termSpinnerController(){
        termSpinner = (Spinner) findViewById(R.id.planTermSpinner);
        termAdapter = ArrayAdapter.createFromResource(this,R.array.term_plan,android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);
        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTermPosition = position;
            }//onItemSelected

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }//departmentSpinnerController

    //Controller for year spinner
    private void yearSpinnerController(){
        yearSpinner = (Spinner) findViewById(R.id.planYearSpinner);
        yearAdapter = ArrayAdapter.createFromResource(this,R.array.future_year,android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = Integer.parseInt((String)yearSpinner.getSelectedItem());
            }//onItemSelected)

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }//departmentSpinnerController

    public void buttonChooseCourseOnClick(){
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SemesterSelectionActivity.this,CourseSelectionActivity.class);
                intent.putExtra("selectedTermPosition",selectedTermPosition);
                intent.putExtra("selectedYear",selectedYear);
                startActivity(intent);
            }//onClick
        });
    }//showCompleteCourseActivity
}//SemesterSelectionActivity


