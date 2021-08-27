package com.cth.myapplication.business;

import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;
import com.cth.myapplication.persistence.CoursePersistence;
import com.cth.myapplication.persistence.Stub.CoursePersistenceStub;
import com.cth.myapplication.persistence.Stub.SemesterPersistenceStub;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessCourseTest extends TestCase {
    private IAccessCourses accessCourses;
    private CoursePersistence coursePersistence;


    @Before
    public void setUp(){
        System.out.println("\nTesting AccessCourses");
        coursePersistence = new CoursePersistenceStub();
        accessCourses = new AccessCourses(coursePersistence);
    }//setUp

    @Test
    public void testGetCourses(){
        System.out.println("\nTesting get courses");
        final List<Course> courses = accessCourses.getCourses();
        for(int i = 0; i < courses.size(); i++){
            assertNotNull(courses.get(i));
        }
        System.out.println("Finished testGetCourses");
    }//testGetCourse

    //Testing the update course -> since course code is unique, get the any course from STUB and updated and compare
    @Test
    public void testUpdateCourse() {
        System.out.println("\nTesting update course");
        final List<Course> courses = accessCourses.getSemesterCourse(new Semester("NONE"));
        final Course testCourse = courses.get(1);
        final String beforeUpdateCourseCode = testCourse.getCourseCode();
        accessCourses.updateCourse(testCourse,new Semester("Fall 2019"));

        final  List<Course> updatedCourses = accessCourses.getSemesterCourse(new Semester("Fall 2019"));
        final Course updatedCourse = updatedCourses.get(0);
        assertEquals(updatedCourse.getCourseCode(),beforeUpdateCourseCode);
        System.out.println("Finished testUpdateCourse");
    }//test update course

    @Test
    public void testGetTermCourse(){
        System.out.println("\nTesting getTermCourse");
        List<Course> termCourse = accessCourses.getTermCourse(Term.Fall);
        assertNotNull(termCourse);
        for(int i = 0; i < termCourse.size(); i++){
            assertTrue(termCourse.get(i).checkTerm(Term.Fall));
        }
        System.out.println("Finished testGetTermCourse");
    }//testGetTermCourse

    @Test
    public void testGetSemesterCourse(){
        System.out.println("\nTesting getSemesterCourse");
        List<Course> semesterCourse = accessCourses.getDepartmentCourse("NONE");
        assertNotNull(semesterCourse);
        for(int i = 0; i < semesterCourse.size(); i++){
            assertTrue(semesterCourse.get(i).getSemesterName().equals("NONE"));
        }//for
        System.out.println("Finished testGetSemesterCourse");
    }//testGetSemesterCourse

    @Test
    public void testGetDepartmentCourse(){
        System.out.println("\nTesting getDepartmentCourse");
        List<Course> departmentCourse = accessCourses.getDepartmentCourse("Computer Science");
        assertNotNull(departmentCourse);
        for(int i = 0; i < departmentCourse.size(); i++){
            assertTrue(departmentCourse.get(i).getDepartment().equals("Computer Science"));
        }//for
        System.out.println("Finished testGetDepartmentCourse");
    }//testGetDepartmentCourse

    @Test
    public void testGetPlanSortedCourse(){
        System.out.println("\nTesting getPlanSortedCourse");
        List<Course> planSortedCourse = accessCourses.getPlanSortedCourse(Term.Fall,"Computer Science",new Semester("NONE"));
        assertNotNull(planSortedCourse);
        for(int i = 0; i < planSortedCourse.size(); i++){
            assertTrue(planSortedCourse.get(i).getSemesterName().equals("NONE"));
            assertTrue(planSortedCourse.get(i).checkTerm(Term.Fall));
            assertTrue(planSortedCourse.get(i).getDepartment().equals("Computer Science"));
        }//for
        System.out.println("Finished testGetSemesterCourse");
    }

    @Test
    public void testSortedCourse(){
        System.out.println("\nTesting getTermDepartmentCourse");
        List<Course> sortedCourse = accessCourses.getSortedCourse(Term.Fall,"Computer Science");
        assertNotNull(sortedCourse);
        for(int i = 0; i < sortedCourse.size(); i++){
            assertTrue(sortedCourse.get(i).checkTerm(Term.Fall));
            assertTrue(sortedCourse.get(i).getDepartment().equals("Computer Science"));
        }//for
        System.out.println("Finished testSortedCourse");
    }//testSortedCourse

    @Test
    public void testSortedCourse2(){
        System.out.println("\nTesting getTermDepartmentCourse2");
        List<Course> sortedCourse = accessCourses.getSortedCourse(Term.Fall,"Computer Science");
        assertNotNull(sortedCourse);
        for(int i = 0; i < sortedCourse.size(); i++){
            assertTrue(sortedCourse.get(i).checkTerm(Term.Fall));
            assertTrue(sortedCourse.get(i).getDepartment().equals("Computer Science") || sortedCourse.get(i).getDepartment().equals("Mathematics"));
        }//for
        System.out.println("Finished testSortedCourse");
    }//testSortedCourse2
}//AccessCourseTests



