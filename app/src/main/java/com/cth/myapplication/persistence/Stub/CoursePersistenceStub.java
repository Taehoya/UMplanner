package com.cth.myapplication.persistence.Stub;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;
import com.cth.myapplication.persistence.CoursePersistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoursePersistenceStub implements CoursePersistence {
    private List<Course> courses;
    public CoursePersistenceStub() {
        courses = new ArrayList<Course>();
        addFakeDatabase();
    }//CoursePersistence

    @Override
    public List<Course> getCourseSequential(){
        return Collections.unmodifiableList(courses);
    }//getCourseSequential

    //Currently this methods get the data by using checkSemester
    //but this will be replace with select * from Students where Term = ? with hsqldb
    @Override
    public List<Course> getTermCourse(Term term) {
        List<Course> returnCourse = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).checkTerm(term)){
                returnCourse.add(courses.get(i));
            }//if
        }//for
        return returnCourse;
    }//getSemesterCourse

    //Currently this methods get the data by using departmentEquals
    //but this will be replace with select * from Students where department = ? with hsqldb
    @Override
    public List<Course> getDepartmentCourse(String department) {
        List<Course> departmentCourse = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getDepartment().equalsIgnoreCase(department)){
                departmentCourse.add(courses.get(i));
            }//if
        }//for
        return departmentCourse;
    }//getDepartmentCourse


    @Override
    public List<Course> getSemesterCourse(Semester semester) {
        List<Course> semesterCourse = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getSemesterName().equalsIgnoreCase(semester.getSemesterName())){
                semesterCourse.add(courses.get(i));
            }//if
        }//for
        return semesterCourse;
    }//getSemesterCourse


    @Override
    public Course updateCourse(Course currentCourse,Semester semester) {
        Course course = null;
        boolean found = false;
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getCourseCode().equalsIgnoreCase(currentCourse.getCourseCode()) && !found){
                course = courses.get(i);
                found = true;
            }//if
        }//for

        if(found){
            course.setCourseSemesterName(semester.getSemesterName());
        }
        return course;
    }//updateCourse


    @Override
    public List<Course> getSemesterNTermCourse(Term term, Semester semester) {
        List<Course> semesterNTermCourse = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getSemesterName().equalsIgnoreCase(semester.getSemesterName()) && courses.get(i).checkTerm(term)){
                semesterNTermCourse.add(courses.get(i));
            }//if
        }//for
        return semesterNTermCourse;
    }//getSemesterNTermCourse


    //Add Fake Database for Iteration 1
    private void addFakeDatabase(){
        // COMP courses
        courses.add(new Course("COMP 1010", "Introductory Computer Science 1", "Computer Science", true ,Term.Fall,3,new Semester("NONE")));
        courses.add(new Course("COMP 1020", "Introductory Computer Science 2", "Computer Science", true, Term.Fall ,3,new Semester("NONE")));
        courses.add(new Course("COMP 2140", "Data Structures and Algorithms", "Computer Science", true, Term.Winter, 3,new Semester("Fall 2019")));
        courses.add(new Course("COMP 3020", "Human-Computer Interaction 1", "Computer Science", false, Term.Summer, 3,new Semester("Fall 2020")));
        courses.add(new Course("COMP 4490", "Computer Graphics 2", "Computer Science", false, Term.Fall, 3,new Semester("Winter 2020")));

        // MATH courses (excluding summer)
        courses.add(new Course("MATH 1220", "Linear Algebra 1", "Mathematics", true, Term.WinterSummer, 3,new Semester("Summer 2021")));
        courses.add(new Course("MATH 1230", "Differential Calculus", "Mathematics", true, Term.FallWinterSummer, 3,new Semester("Summer 2020")));
        courses.add(new Course("MATH 1500", "Introduction to Calculus", "Mathematics", true, Term.WinterSummer, 3,new Semester("Fall 2019")));
        courses.add(new Course("MATH 3390", "Introduction to Topology", "Mathematics", false, Term.Fall, 3,new Semester("Fall 2019")));
        courses.add(new Course("MATH 4390", "Numerical Approximation Theory", "Mathematics", false, Term.Summer, 3,new Semester("Fall 2020")));
        courses.add(new Course("MATH 4450", "Number Theory 2", "Mathematics", false, Term.WinterSummer, 3,new Semester("Summer 2020")));
    }//Add
}//CoursePersistence Dummy class



