package com.cth.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.cth.myapplication.R;
import com.cth.myapplication.business.AccessCourses;
import com.cth.myapplication.business.AccessSemester;
import com.cth.myapplication.business.IAccessCourses;
import com.cth.myapplication.business.IAccessSemester;
import com.cth.myapplication.business.SearchCourseList;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;
import com.cth.myapplication.presentation.Adapter.CourseAdapter;
import com.cth.myapplication.presentation.Adapter.FinalizeDialog;

import java.util.ArrayList;
import java.util.List;

public class CourseSelectionActivity extends AppCompatActivity implements FinalizeDialog.FinalizeDialogListener {

    private ListView availableCourseListView;
    private ListView semesterCourseListView;
    private ArrayList<Course> semesterCourseList;
    private ArrayList<Course> filteredCourse;
    private IAccessCourses accessCourses;
    private IAccessSemester accessSemester;
    private Spinner departmentSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private String selectedDepartment;
    private Term selectedTerm;
    private Semester semester;
    TextView semesterNameText;
    private String semesterName;
    private Button finalizeButton;
    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);
        accessCourses = new AccessCourses();
        accessSemester = new AccessSemester();
        filteredCourse = new ArrayList<Course>();
        searchView = findViewById(R.id.completedSearchTextBox);
        //Method that create semester
        makeSemester();
        //Method that set textView for semester
        setSemesterText();
        //Method that control department Spinner
        departmentSpinnerController();
        //Method that control listView
        setAvailableCourseList();
        setSemesterCourseList();
        //Method that control search
        initSearchWidget();
        //Method that control course selection
        courseSelectionController();
        //Method that control finalize button
        finalizeButtonController();
    }//onCreate


    private void courseSelectionController(){
        availableCourseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = (Course)(availableCourseListView.getItemAtPosition(position));
                accessCourses.updateCourse(course,semester);
                setSemesterCourseList();
                setAvailableCourseList();
            }
        });

        semesterCourseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = (Course)(semesterCourseListView.getItemAtPosition(position));
                accessCourses.updateCourse(course,new Semester("NONE"));
                setSemesterCourseList();
                setAvailableCourseList();
            }
        });
    }//courseSelectionController

    private void makeSemester(){
        int selectedTermPosition = getIntent().getIntExtra("selectedTermPosition", 0);
        selectedTerm = selectedTermPosition == 0? Term.Fall: selectedTermPosition ==1? Term.Winter: Term.Summer;
        int selectedYear = getIntent().getIntExtra("selectedYear", 0);
        semester = new Semester(selectedTerm, selectedYear);
        semesterName = semester.getSemesterName();
    }//makeSemester

    private void setSemesterText(){
        semesterNameText = (TextView)findViewById(R.id.courseSelectionSemesterName);
        semesterNameText.setText(semester.getSemesterName());
    }//setSemesterText

    private void setSemesterCourseList(){
        semesterCourseList = new ArrayList<Course>();
        semesterCourseList.addAll(accessCourses.getSemesterCourse(semester));
        semesterCourseListView = findViewById(R.id.selectedCourseList);
        CourseAdapter adapter = new CourseAdapter(getApplicationContext(),0,semesterCourseList);
        semesterCourseListView.setAdapter(adapter);
    }

    //Method that link adapter with customized list view
    private void setAvailableCourseList(){
        ArrayList<Course> availableCourseList = new ArrayList<Course>();
        if(departmentSpinner.getSelectedItemPosition() == 0){
            availableCourseList.addAll(accessCourses.getPlanSortedCourse(selectedTerm,null,new Semester("NONE")));
        }else{
            availableCourseList.addAll(accessCourses.getPlanSortedCourse(selectedTerm,selectedDepartment,new Semester("NONE")));
        }
        availableCourseListView = findViewById(R.id.availableCourseList);
        CourseAdapter adapter = new CourseAdapter(getApplicationContext(),0, availableCourseList);
        availableCourseListView.setAdapter(adapter);
    }//setCourseList


    private void departmentSpinnerController(){
        departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
        ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(this, R.array.Department, android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDepartment = (String)departmentSpinner.getSelectedItem();
                filteredCourse.clear();
                searchView.setQuery("", false);
                if(departmentSpinner.getSelectedItemPosition() == 0){
                    filteredCourse.addAll(accessCourses.getPlanSortedCourse(selectedTerm,null,new Semester("NONE")));
                }else{
                    filteredCourse.addAll(accessCourses.getPlanSortedCourse(selectedTerm,selectedDepartment,new Semester("NONE")));
                }
                CourseAdapter adapter = new CourseAdapter(getApplicationContext(),0,filteredCourse);
                availableCourseListView.setAdapter(adapter);
            }//onItemSelected

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }//departmentSpinnerController

    //Setting the search widget
    private void initSearchWidget(){
        SearchView searchView = (SearchView)findViewById(R.id.completedSearchTextBox);
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
                availableCourseListView.setAdapter(adapter);
                return false;
            }//onQueryTextChange
        });
    }//initSearchWidget

    private void finalizeButtonController(){
        finalizeButton = (Button)findViewById(R.id.finalizeButton);
        finalizeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               showDialog();
            }
        });
    }//finalizeButtonController

    public void showDialog(){
        DialogFragment dialog = new FinalizeDialog();
        dialog.show(getSupportFragmentManager(),"Finalize Dialog");
    }

    @Override
    public void onYesClicked() {
        accessSemester.insertSemester(semester);
        Intent intent = new Intent(CourseSelectionActivity.this,PlanListActivity.class);
        startActivity(intent);
    }
}//CourseSelectionActivity
