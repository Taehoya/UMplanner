package com.cth.myapplication.business;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.persistence.SemesterPersistence;
import com.cth.myapplication.persistence.Stub.SemesterPersistenceStub;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccessSemesterTest {
    private IAccessSemester accessSemester;
    private SemesterPersistence semesterPersistence;

    @Before
    public void setUp(){
        System.out.println("\nTesting AccessSemesters");
        semesterPersistence = new SemesterPersistenceStub();
        accessSemester = new AccessSemester(semesterPersistence);
        accessSemester.insertSemester(new Semester("Summer 2019"));
        accessSemester.insertSemester(new Semester("Fall 2023"));
    }//setUp

    @Test
    public void testGetSemesters(){
        System.out.println("\nTesting getSemesters");
        List<Semester> testSemesters = accessSemester.getSemesters();
        assertNotNull(testSemesters);
        for(int i = 0; i < testSemesters.size(); i++){
            assertNotNull(testSemesters.get(i));
        }
        assertEquals(2,testSemesters.size());
        System.out.println("Finished testGetSemesters");
    }//testGetTermCourse

    @Test
    public void testInsertSemesters() {
        System.out.println("\nTesting insert semesters");
        final Semester semester = new Semester("Fall 2020");
        accessSemester.insertSemester(semester);
        assertEquals(3, accessSemester.getSemesters().size());
        System.out.println("Finished insert semesters");
    }

    @Test
    public void testDeleteSemester() {
        System.out.println("\n Testing delete Semester");
        List<Semester> semesters = accessSemester.getSemesters();
        final Semester semester = semesters.get(0);
        assertEquals(2, semesters.size());
        accessSemester.deleteSemester(semester);
        semesters = accessSemester.getSemesters();
        assertEquals(1, semesters.size());
        System.out.println("Finished delete semester");
    }
}
