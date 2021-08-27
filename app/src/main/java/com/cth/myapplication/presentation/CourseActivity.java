package com.cth.myapplication.presentation;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.Nullable;


import com.cth.myapplication.R;
import com.cth.myapplication.business.AccessCourses;
import com.cth.myapplication.business.IAccessCourses;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Term;
import com.cth.myapplication.business.SearchCourseList;
import com.cth.myapplication.presentation.Adapter.CourseAdapter;


import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends Activity {
    private ListView listView;
    private ArrayList<Course> courseList;
    private ArrayList<Course> filteredCourse;
    private IAccessCourses accessCourses;
    private ArrayAdapter departmentAdapter;
    private Spinner departmentSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private SearchView searchView;
    private String selectedDepartment;
    private Term selectedTerm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        filteredCourse = new ArrayList<Course>();
        courseList = new ArrayList<Course>();
        accessCourses = new AccessCourses();
        courseList.addAll(accessCourses.getCourses());

        //Method that control listView
        setCourseList();
        //Method that control Spinner
        termSpinnerController();
        departmentSpinnerController();
        //Method that control search
        initSearchWidget();
    }//onCreate


    //Method that link adapter with customized list view
    private void setCourseList(){
        listView = findViewById(R.id.courseList);
        CourseAdapter adapter = new CourseAdapter(getApplicationContext(),0,courseList);
        listView.setAdapter(adapter);
    }//setCourseList

    //Method that control term spinner
    private void termSpinnerController(){
        termSpinner = (Spinner) findViewById(R.id.termSpinner);
        termAdapter = ArrayAdapter.createFromResource(this,R.array.term,android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);
        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filteredCourse.clear();
                searchView.setQuery("", false);
                selectedTerm = position == 0? Term.FallWinterSummer: position ==1? Term.Fall: position ==2? Term.Winter : Term.Summer;
                selectedDepartment = (String)departmentSpinner.getSelectedItem();
                if(departmentSpinner.getSelectedItemPosition() == 0){
                    filteredCourse.addAll(accessCourses.getSortedCourse(selectedTerm,null));
                }else{
                    filteredCourse.addAll(accessCourses.getSortedCourse(selectedTerm,selectedDepartment));
                }
                filteredCourse.addAll(accessCourses.getSortedCourse(selectedTerm,selectedDepartment));
                CourseAdapter adapter = new CourseAdapter(getApplicationContext(),0,filteredCourse);
                listView.setAdapter(adapter);
            }//onItemSelected

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }//termSpinnerController

    //Method that control department spinner
    private void departmentSpinnerController(){
        departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
        departmentAdapter = ArrayAdapter.createFromResource(this,R.array.Department,android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDepartment = (String)departmentSpinner.getSelectedItem();
                filteredCourse.clear();
                searchView.setQuery("", false);
                if(departmentSpinner.getSelectedItemPosition() == 0){
                    filteredCourse.addAll(accessCourses.getSortedCourse(selectedTerm,null));
                }else{
                    filteredCourse.addAll(accessCourses.getSortedCourse(selectedTerm,selectedDepartment));
                }
                CourseAdapter adapter = new CourseAdapter(getApplicationContext(),0,filteredCourse);
                listView.setAdapter(adapter);
            }//onItemSelected

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }//departmentSpinnerController

    //Setting the search widget
    private void initSearchWidget(){
        searchView = (SearchView)findViewById(R.id.searchText);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            //This is called whenever we search
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }//onQuerySubmit
            @Override
            public boolean onQueryTextChange(String newText) {
                List<Course> searchedCourse = SearchCourseList.searchEngine(filteredCourse,newText);
                CourseAdapter adapter = new CourseAdapter(getApplicationContext(),0,searchedCourse);
                listView.setAdapter(adapter);
                return false;
            }//onQueryTextChange
        });
    }//initSearchWidget
}//CourseActivity





