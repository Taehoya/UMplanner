package com.cth.myapplication.persistence;

import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;

import java.util.List;

public interface CoursePersistence {
    //Method that return the courses
    List<Course> getCourseSequential();
    //Method that return the course that specific term provide
    List<Course> getTermCourse(Term term);
    //Method that return the course that department provide
    List<Course> getDepartmentCourse(String department);
    //Method that return the course that have attribute 'semester'
    List<Course> getSemesterCourse(Semester semester);
    //Method that update the course
    Course updateCourse(Course currentCourse,Semester semesterName);
    //Method that return the course that term and semester attribute
    public List<Course> getSemesterNTermCourse(Term term, Semester semester);
}//CoursePersistence
