package com.cth.myapplication.persistence;
import com.cth.myapplication.objects.Semester;

import java.util.List;

public interface SemesterPersistence {
    //Method to insert semester
    Semester insertSemester(Semester semester);
    //Method that return the semester
    List<Semester> getSemesterSequential();
    //Delete Semester
    void deleteSemester(Semester semester);
}//SemesterPersistence
