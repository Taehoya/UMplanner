package com.cth.myapplication.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class SemesterTest {

    @Test
    public void testSemester1(){
        Semester semester;
        System.out.println("\nStarting testSemester1");
        semester = new Semester(Term.Fall,2019);
        assertNotNull(semester);
        assertEquals("Fall 2019", semester.getSemesterName());
        assertEquals(Term.Fall.toString(),semester.getTerm());
        assertEquals(2019,semester.getYear());
        System.out.println("Finished testSemester1");
    }//testCourse1

    @Test
    public void testSemester2(){
        Semester semester;
        System.out.println("\nStarting testSemester2");
        semester = new Semester("Winter 2020");
        assertNotNull(semester);
        assertEquals("Winter 2020", semester.getSemesterName());
        assertNotEquals(Term.Fall.toString(),semester.getTerm());
        assertEquals(2020,semester.getYear());
        System.out.println("Finished testSemester2");
    }//testCourse1

    @Test
    public void testSemester3(){
        Semester semester;
        System.out.println("\nStarting testSemester1");
        semester = new Semester("NONE");
        assertNotNull(semester);
        assertEquals("NONE", semester.getSemesterName());
        assertNotEquals(Term.Fall.toString(),semester.getTerm());
        assertNotEquals(2020,semester.getYear());
        System.out.println("Finished testSemester3");
    }//testCourse1
}
