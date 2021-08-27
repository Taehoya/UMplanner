package com.cth.myapplication.business;

import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;
import com.cth.myapplication.persistence.SemesterPersistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccessSemesterMockTest {

    private List<Semester> semesterList;

    @Mock
    private SemesterPersistence semesterPersistence;

    @Before
    public void setup(){
        semesterList = new ArrayList<Semester>();
        semesterList.add(new Semester(Term.Fall, 2018));
        semesterList.add(new Semester(Term.Winter, 2019));
        semesterList.add(new Semester(Term.Winter, 2021));
        semesterList.add(new Semester(Term.Fall, 2021));
        semesterList.add(new Semester(Term.Summer, 2021));
        semesterList.add(new Semester(Term.Winter, 2022));
    }

    @Test
    public void currentSemesterTest(){
        assertNotNull(semesterPersistence);
        when(semesterPersistence.getSemesterSequential()).thenReturn(semesterList);
        IAccessSemester accessSemester = new AccessSemester(semesterPersistence);
        assertEquals(accessSemester.getCurrentSemester().size(), 1);
    }

    @Test
    public void pastSemesterTest(){
        assertNotNull(semesterPersistence);
        when(semesterPersistence.getSemesterSequential()).thenReturn(semesterList);
        IAccessSemester accessSemester = new AccessSemester(semesterPersistence);
        assertEquals(accessSemester.getPastSemesters().size(), 2);
    }

    @Test
    public void futureSemesterTest(){
        assertNotNull(semesterPersistence);
        when(semesterPersistence.getSemesterSequential()).thenReturn(semesterList);
        IAccessSemester accessSemester = new AccessSemester(semesterPersistence);
        assertEquals(accessSemester.getFutureSemesters().size(), 3);
    }
}
