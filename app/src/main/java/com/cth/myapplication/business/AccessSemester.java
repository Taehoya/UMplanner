package com.cth.myapplication.business;

import com.cth.myapplication.application.Services;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.persistence.SemesterPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessSemester implements IAccessSemester{
    private SemesterPersistence semesterPersistence;
    private List<Semester> semesters;
    private IAccessCourses accessCourses;

    //AccessSemester Constructor
    public AccessSemester() {
        this(Services.getSemesterPersistence());
    }//AccessSemesters Constructor

    ///AccessSemester Constructor
    public AccessSemester(final SemesterPersistence semesterPersistence) {
        this.semesterPersistence = semesterPersistence;
        accessCourses = new AccessCourses();
        semesters = null;
    }//AccessSemester

    //Get the semesters in the Data Layer
    public List<Semester> getSemesters() {
        semesters = semesterPersistence.getSemesterSequential();
        return Collections.unmodifiableList(semesters);
    }//getSemesters

    //insert semester in the Data Layer
    public Semester insertSemester(Semester semester){
        return semesterPersistence.insertSemester(semester);
    }//insertSemester

    //delete semester in the Data Layer
    public void deleteSemester(Semester semester){
        semesterPersistence.deleteSemester(semester);
    }//deleteSemester

    @Override
    public List<Semester> getFutureSemesters(){
        List<Semester> semesters = semesterPersistence.getSemesterSequential();
        List<Semester> futureSemesters = new ArrayList<Semester>();
        Semester currentSemester = Semester.getCurrentSemester();
        for(int i = 0; i < semesters.size(); i++){
            if(semesters.get(i).compareTo(currentSemester) > 0){
                futureSemesters.add(semesters.get(i));
            }//if
        }//for
        return futureSemesters;
    }//getFutureSemesterCourse
    @Override
    public List<Semester> getPastSemesters() {
        List<Semester> semesters = semesterPersistence.getSemesterSequential();
        List<Semester> pastSemesters = new ArrayList<Semester>();
        Semester currentSemester = Semester.getCurrentSemester();
        for(int i = 0; i < semesters.size(); i++){
            if(semesters.get(i).compareTo(currentSemester) < 0){
                pastSemesters.add(semesters.get(i));
            }//if
        }//for
        return pastSemesters;
    }//getPastSemesters
    @Override
    public List<Semester> getCurrentSemester() {
        List<Semester> semesters = semesterPersistence.getSemesterSequential();
        List<Semester> currentSemesterList = new ArrayList<Semester>();
        Semester currentSemester = Semester.getCurrentSemester();
        for(int i = 0; i < semesters.size(); i++){
            if(semesters.get(i).compareTo(currentSemester) == 0){
                currentSemesterList.add(semesters.get(i));
            }//if
        }//for
        return currentSemesterList;
    }//getCurrentSemesters
}//AccessSemester



