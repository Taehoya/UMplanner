package com.cth.myapplication.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cth.myapplication.R;
import com.cth.myapplication.business.AccessCourses;
import com.cth.myapplication.business.AccessSemester;
import com.cth.myapplication.business.IAccessCourses;
import com.cth.myapplication.business.IAccessSemester;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;
import com.cth.myapplication.presentation.Adapter.SemesterAdapter;

import java.util.ArrayList;

public class PlanListActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<Semester> semesterList;
    private IAccessSemester accessSemester;
    private IAccessCourses accessCourses;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_list_home);
        semesterList = new ArrayList<Semester>();
        accessCourses = new AccessCourses();
        accessSemester = new AccessSemester();
        //setting the list of semester
        setSemesterList();
    }//onCreate

    public void setSemesterList(){
        semesterList.addAll(accessSemester.getSemesters());
        listview = findViewById(R.id.semesterList);
        SemesterAdapter adapter = new SemesterAdapter(this,0,semesterList);
        listview.setAdapter(adapter);
    }//setSemesterList

    public void buttonAddPlanOnClick(View view){
        PlanListActivity.this.startActivity(new Intent(PlanListActivity.this, SemesterSelectionActivity.class));
    }//showLookUpCourseActivity
}//PlanListActivity
