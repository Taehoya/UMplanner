package com.cth.myapplication.business;
import com.cth.myapplication.objects.Semester;
import java.util.List;

public interface IAccessSemester {
    //Get the semesters
    List<Semester> getSemesters();
    //insert semester in the Data Layer
    Semester insertSemester(Semester semester);
    //Delete semester
    void deleteSemester(Semester semester);

    List<Semester> getFutureSemesters();

    List<Semester> getPastSemesters();

    List<Semester> getCurrentSemester();
}
