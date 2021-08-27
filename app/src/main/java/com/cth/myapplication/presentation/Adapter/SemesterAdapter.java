package com.cth.myapplication.presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.AdapterListUpdateCallback;

import com.cth.myapplication.R;
import com.cth.myapplication.business.AccessCourses;
import com.cth.myapplication.business.AccessSemester;
import com.cth.myapplication.business.IAccessCourses;
import com.cth.myapplication.business.IAccessSemester;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.presentation.CourseSelectionActivity;
import com.cth.myapplication.presentation.CurrentSemesterActivity;
import com.cth.myapplication.presentation.PlanListActivity;

import java.util.ArrayList;
import java.util.List;

public class SemesterAdapter extends ArrayAdapter<Semester>{
    IAccessCourses accessCourses;
    IAccessSemester accessSemester;
    List<Course> semesterCourseList;
    Semester semester;
    private Context mContext;

    public SemesterAdapter(Context context, int resource, List<Semester> semesterList ) {
        super(context, resource, semesterList);
        mContext = context;
    }//CourseAdapter Constructor

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        semester = getItem(position);
        accessCourses = new AccessCourses();
        accessSemester = new AccessSemester();
        semesterCourseList = new ArrayList<Course>();
        semesterCourseList.addAll(accessCourses.getSemesterCourse(semester));
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.semester_item, parent, false);
        }
        TextView semesterName = convertView.findViewById(R.id.semesterNameText);
        semesterName.setText(semester.getSemesterName());
        ListView semesterCourseListView = convertView.findViewById(R.id.semesterPlannedCourseList);
        CourseAdapter adapter = new CourseAdapter(getContext(),0,semesterCourseList);
        semesterCourseListView.setAdapter(adapter);
        Button removeSemesterButton = convertView.findViewById(R.id.removeSemesterButton);
        removeSemesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semester = getItem(position);
                accessSemester.deleteSemester(semester);
                List<Course> courses = accessCourses.getSemesterCourse(semester);
                accessCourses.updateAllCourse(courses,new Semester("NONE"));
                ((PlanListActivity)mContext).setSemesterList();
                if(mContext instanceof PlanListActivity){
                    ((PlanListActivity)mContext).setSemesterList();
                    ((PlanListActivity)mContext).recreate();
                }//if
            }//onClick
        });
        // Hide the remove button unless in manage plan activity
        if(mContext instanceof PlanListActivity == false)
            removeSemesterButton.setVisibility(View.INVISIBLE);
        // Set Semester height to match parent for current semester activity
        if(mContext instanceof CurrentSemesterActivity){
            LinearLayout semesterCard = convertView.findViewById(R.id.semesterCard);
            ViewGroup.LayoutParams params = semesterCard.getLayoutParams();
            params.height = 1750;
            semesterCard.setLayoutParams(params);
        }
        return convertView;
    }//getView
}//SemesterAdapter
