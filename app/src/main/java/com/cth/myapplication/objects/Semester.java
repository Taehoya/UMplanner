package com.cth.myapplication.objects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Semester {
    //Variable that store the semester name
    private String semesterName;
    //Variable that store term
    private Term term;
    //Variable that store year
    private int year;

    //Semester Constructor with term and year
    public Semester(Term term, int year) {
        this.term = term;
        this.year = year;
        this.semesterName = term.toString() +" " + year;
    }//Semester Constructor with term and year

    //Semester Constructor with semester Name
    public Semester(String semesterName){
        if(semesterName.equalsIgnoreCase("NONE")){
            this.semesterName = "NONE";
            this.year = Integer.MIN_VALUE;
            this.term = Term.None;
        }else{
            this.semesterName = semesterName;
            String[] array = semesterName.split("\\s+");
            this.term = Term.valueOf(array[0]);
            this.year = Integer.parseInt(array[1]);
        }//else
    }//Semester Constructor with semester name

    //Get the current semester
    //Currently it is Winter 2021
    public static Semester getCurrentSemester(){
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        Term thisTerm = (month >=1 && month <=4) ? Term.Winter :  (month >=5 && month <=8) ? Term.Summer : Term.Fall;
        int year = cal.get(Calendar.YEAR);
        return new Semester(thisTerm,year);
    }//getCurrentSemester

    public int compareTo(Semester semester){
        int returnValue = Integer.MIN_VALUE;
        if(semester.year > this.year){
            returnValue =  -1;
        }else if(semester.year < this.year){
            returnValue = 1;
        }else{
            if(getTermOrder(semester.term) > getTermOrder(this.term)){
                returnValue =  -1;
            }else if(getTermOrder(semester.term) < getTermOrder(this.term)){
                returnValue =  1;
            }else{
                returnValue =  0;
            }
        }
        return returnValue;
    }//compareTo

    //Getter for semester name
    public String getSemesterName() {
        return semesterName;
    }//getSemesterName
    //Getter for term
    public String getTerm() {
        return term.toString();
    }//getTerm
    //Getter for year
    public int getYear() { return year; }//getYear
    //Setter for semester name
    public void setSemesterName(String semesterName){
        this.semesterName = semesterName;
        String[] array = semesterName.split("\\s+");
        this.term = Term.valueOf(array[0]);
        this.year = Integer.parseInt(array[1]);
    }//setSemesterName

    //Get the integer value corresponding to the term value
    //Winter is 1, Summer is 2, and Fall is 3
    //This is because, winter 2021 < Summer 2021 < Fall 2021
    private int getTermOrder(Term term){
        return term == Term.Winter ? 1 : term == Term.Summer ? 2 : 3;
    }//getTermOrder
}//Semester Class

