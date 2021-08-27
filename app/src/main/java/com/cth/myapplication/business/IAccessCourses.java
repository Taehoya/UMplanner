package com.cth.myapplication.business;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;
import java.util.List;

public interface IAccessCourses {
    //Get the all courses from data layer
    List<Course> getCourses();
    //Update course with semester value
    Course updateCourse(Course course, Semester semester);
    //Update all the courses with semester value
    void updateAllCourse(List<Course> courses,Semester semester);
    //Get the course that provided that 'term'
    List<Course> getTermCourse(Term term);
    //Get the course that 'department' has
    List<Course> getDepartmentCourse(String department);
    //Get the course 'semester'
    List<Course> getSemesterCourse(Semester semester);
    //Get the list of courses that open 'term' and 'department'
    List<Course> getPlanSortedCourse(Term term, String department,Semester semester);
    //Get list of courses and then return the list that all list has
    List<Course> getSortedCourse(Term term,String department);
}//IAccessCourses
