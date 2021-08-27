package com.cth.myapplication.presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cth.myapplication.R;
import com.cth.myapplication.objects.Course;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {
    public CourseAdapter(Context context, int resource, List<Course> courseLit){
        super(context,resource,courseLit);
    }//CourseAdapter Constructor


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Course course = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_item, parent, false);
        }

        TextView courseCode = convertView.findViewById(R.id.courseCodeText);
        TextView courseName = convertView.findViewById(R.id.courseNameText);
        TextView department = convertView.findViewById(R.id.departmentText);
        TextView labText = convertView.findViewById(R.id.labText);
        TextView semesterText = convertView.findViewById(R.id.semesterText);
        TextView creditText = convertView.findViewById(R.id.creditText);

        courseCode.setText(course.getCourseCode());
        courseName.setText(course.getCourseName());
        department.setText(course.getDepartment());
        labText.setText(course.getLab());
        semesterText.setText(course.getTermString());
        creditText.setText(course.getCreditHour());

        return convertView;
    }//getView
}//CourseAdapter


