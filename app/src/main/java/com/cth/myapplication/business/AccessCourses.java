package com.cth.myapplication.business;
import com.cth.myapplication.application.Services;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.persistence.CoursePersistence;
import com.cth.myapplication.objects.Term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessCourses implements IAccessCourses{
    private CoursePersistence coursePersistence;
    private List<Course> courses;

    public AccessCourses() {
        this(Services.getCoursePersistence());
    }//AccessCourses Constructor

    public AccessCourses(final CoursePersistence coursePersistence) {
        this.coursePersistence = coursePersistence;
        courses = null;
    }

    public List<Course> getCourses() {
        courses = coursePersistence.getCourseSequential();
        return Collections.unmodifiableList(courses);
    }//getCourses

    public Course updateCourse(Course course,Semester semester){
        return coursePersistence.updateCourse(course,semester);
    }//updateCourse

    public void updateAllCourse(List<Course> courses,Semester semester){
        for(int i = 0; i < courses.size(); i++){
            coursePersistence.updateCourse(courses.get(i),semester);
        }//for
    }//updateAllCourse

    public List<Course> getTermCourse(Term term){
        List<Course> semesterCourse = new ArrayList<>();
        semesterCourse = coursePersistence.getTermCourse(term);
        return Collections.unmodifiableList(semesterCourse);
    }//getTermCourse

    public List<Course> getDepartmentCourse(String department){
        List<Course> departmentCourse = new ArrayList<>();
        departmentCourse = coursePersistence.getDepartmentCourse(department);
        return Collections.unmodifiableList(departmentCourse);
    }//getDepartmentCourse

    public List<Course> getSemesterCourse(Semester semester){
        List<Course> semesterCourse = new ArrayList<>();
        semesterCourse = coursePersistence.getSemesterCourse(semester);
        return Collections.unmodifiableList(semesterCourse);
    }//getSemesterCourse


    public List<Course> getPlanSortedCourse(Term term, String department,Semester semester){
        List<Course> sortedCourse = new ArrayList<Course>();
        List<Course> termSortedCourse;
        //First get the course that only open during that Term
        if(term.checkTerm(Term.FallWinterSummer)){
            termSortedCourse = coursePersistence.getSemesterCourse(new Semester("NONE"));
        }else{
            termSortedCourse = coursePersistence.getSemesterNTermCourse(term,semester);
        }
        //And then sorted with department
        if(department != null){
            for(int i = 0; i< termSortedCourse.size(); i++){
                if(termSortedCourse.get(i).getDepartment().equalsIgnoreCase(department)){
                    sortedCourse.add(termSortedCourse.get(i));
                }//if
            }//for
        }else{
            sortedCourse.addAll(termSortedCourse);
        }
        return Collections.unmodifiableList(sortedCourse);
    }

    //Get list of courses and then return the list that all list has
    public List<Course> getSortedCourse(Term term,String department){
        List<Course> sortedCourse = new ArrayList<Course>();
        List<Course> termSortedCourse;
        //First get the course that only open during that Term
        if(term.checkTerm(Term.FallWinterSummer)){
            termSortedCourse = coursePersistence.getCourseSequential();
        }else{
            termSortedCourse = coursePersistence.getTermCourse(term);
        }
        //And then sorted with department
        if(department != null){
            for(int i = 0; i< termSortedCourse.size(); i++){
                if(termSortedCourse.get(i).getDepartment().equalsIgnoreCase(department)){
                    sortedCourse.add(termSortedCourse.get(i));
                }//if
            }//for
        }else{
            sortedCourse.addAll(termSortedCourse);
        }
        return Collections.unmodifiableList(sortedCourse);
    }//getSortedCourse

}//AccessCourses
