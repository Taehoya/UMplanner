package com.cth.myapplication.objects;

public class Course {
    private final String courseCode;
    private final String courseName;
    private final String department;
    private final boolean lab;
    private final int creditHour;
    private final Term term;
    private Semester semester;

    //Course Constructor
    public Course(String courseCode, String courseName, String department, boolean lab, Term term, int creditHour,Semester semester) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.department = department;
        this.lab = lab;
        this.term = term;
        this.creditHour = creditHour;
        this.semester = semester;
    }//Course Constructor

    //getter for courseCode
    public String getCourseCode() {
        return courseCode;
    }
    //getter for courseName
    public String getCourseName() {
        return courseName;
    }
    //getter for department
    public String getDepartment() {
        return department;
    }
    //getter for lab
    public boolean isLab() {
        return lab;
    }
    //getter for lab(String
    public String getLab(){
        return lab ? "Lab" : "No Lab";
    }//getLab
    //getter for credit Hour (String)
    public String getCreditHour() {
        return creditHour + " credits";
    }
    //getter for credit Hour (int)
    public int getCreditHourInt(){return creditHour;}
    //equals method that return true such that both strings are the same
    public Term getTerm(){ return this.term;}
    //Method that check term
    public boolean checkTerm(Term term){
        return this.term.checkTerm(term);
    }//checkTerm
    //Method that get semester name
    public String getSemesterName(){ return this.semester.getSemesterName();}
    //Method setter for courseSemesterName
    public void setCourseSemesterName(String semesterName){this.semester.setSemesterName(semesterName);}
    //Method that return the string such that
    //which term they provide for the course
    public String getTermString(){
        String returnString = this.term.isFall() ? "Fall": "";
        returnString += this.term.isWinter() ? " Winter": "";
        returnString += this.term.isSummer() ? " Summer": "";
        return returnString.trim();
    }//getSemester
}//Courses
