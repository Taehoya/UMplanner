package com.cth.myapplication.presentation;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cth.myapplication.R;
import com.cth.myapplication.business.AccessSemester;
import com.cth.myapplication.business.IAccessSemester;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.presentation.Adapter.SemesterAdapter;

import java.util.ArrayList;

public class CurrentSemesterActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<Semester> semesterList;
    private IAccessSemester accessSemester;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semester_list_template);
        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText("Current");

        semesterList = new ArrayList<Semester>();
        accessSemester = new AccessSemester();

        setSemesterList();
    }

    public void setSemesterList(){
        semesterList.addAll(accessSemester.getCurrentSemester());
        listview = findViewById(R.id.semesterList);
        SemesterAdapter adapter = new SemesterAdapter(this,0,semesterList);
        listview.setAdapter(adapter);
    }
}
