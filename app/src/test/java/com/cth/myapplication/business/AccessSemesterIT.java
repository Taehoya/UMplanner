package com.cth.myapplication.business;

import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;
import com.cth.myapplication.persistence.CoursePersistence;
import com.cth.myapplication.persistence.SemesterPersistence;
import com.cth.myapplication.persistence.hsqldb.CoursePersistenceHSQLDB;
import com.cth.myapplication.persistence.hsqldb.SemesterPersistenceHSQLDB;
import com.cth.myapplication.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessSemesterIT {

    private IAccessSemester accessSemester;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final SemesterPersistence persistence = new SemesterPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessSemester = new AccessSemester(persistence);
        accessSemester.insertSemester(new Semester("Fall 2019"));
        accessSemester.insertSemester(new Semester("Winter 2020"));
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

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}//AccessSemesterIT
