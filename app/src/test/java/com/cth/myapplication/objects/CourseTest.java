package com.cth.myapplication.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CourseTest {
    @Test
    public void testCourse1(){
        Course course;
        System.out.println("\nStarting testCourse");
        course = new Course("COMP1010","Introductory of Computer Science","Computer Science",false,Term.FallSummer,3,new Semester("NONE"));
        assertNotNull(course);
        assertEquals("COMP1010", course.getCourseCode());
        assertEquals("Introductory of Computer Science",course.getCourseName());
        assertEquals("Computer Science", course.getDepartment());
        assertEquals(false,course.isLab());
        assertEquals("No Lab",course.getLab());
        assertEquals("3 credits",course.getCreditHour());
        assertEquals("3 credits",course.getCreditHour());
        assertEquals(3, course.getCreditHourInt());
        assertEquals(Term.FallSummer,course.getTerm());
        assertEquals(true, course.checkTerm(Term.Fall));
        assertEquals(false, course.checkTerm(Term.Winter));
        assertEquals(true, course.checkTerm(Term.Summer));
        assertEquals("Fall Summer",course.getTermString());
        System.out.println("Finished testCourse");
    }//testCourse1
}//CourseTest
