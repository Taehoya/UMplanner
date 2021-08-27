package com.cth.myapplication.persistence.Stub;

import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.persistence.SemesterPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SemesterPersistenceStub implements SemesterPersistence {
    private List<Semester> semesters;

    public SemesterPersistenceStub() {
        semesters = new ArrayList<Semester>();
    }//semesterPersistenceStub

    @Override
    public Semester insertSemester(Semester semester) {
        boolean found = false;
        for(int i = 0; i < semesters.size(); i++){
            if(semesters.get(i).getSemesterName().equalsIgnoreCase(semester.getSemesterName())){
                found = true;
            }//if
        }//for
        if(!found){
            semesters.add(semester);
        }//if
        return semester;
    }//insertSemester

    @Override
    public List<Semester> getSemesterSequential() {
        return Collections.unmodifiableList(semesters);
    }//getSemesterSequential

    @Override
    public void deleteSemester(Semester semester) {
        int foundPosition = Integer.MAX_VALUE;
        for(int i = 0; i <semesters.size() && foundPosition == Integer.MAX_VALUE; i++){
            if(semesters.get(i).getSemesterName().equalsIgnoreCase(semester.getSemesterName())){
                foundPosition = i;
            }//if
        }//for

        if(foundPosition != Integer.MAX_VALUE){
            semesters.remove(foundPosition);
        }//if
    }//deleteSemester
}//SemesterPersistenceStub
