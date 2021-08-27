package com.cth.myapplication.business;

import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SearchCourseListTest extends TestCase {
    List<Course> list;
    Course course1;
    Course course2;
    Course course3;
    @Before
    public void setUp(){
        System.out.println("Testing SearchCourseListTest");
        list = new ArrayList<Course>();
        course1 = new Course("COMP 1020", "Introductory Computer Science 2", "Computer Science", true, Term.FallWinterSummer, 3, new Semester("Fall 2019"));
        course2 = new Course("COMP 2080", "Analysis of Algorithms", "Computer Science", false, Term.Fall, 3, new Semester("Fall 2020"));
        course3 = new Course("COMP 2140", "Data Structures and Algorithms", "Computer Science", true, Term.Winter, 3, new Semester("Winter 2019"));

        list.add(course1);
        list.add(course2);
        list.add(course3);

    }//setUp

    @Test
    public void testSearchEngine(){
        System.out.println("\nTesting searchEngineTest");
        String userInput = "COMP 1010";
        List<Course> testSearch = SearchCourseList.searchEngine(list,"COMP 1010");
        for(int i = 0 ; i < testSearch.size(); i++){
            assertEquals(testSearch.get(i).getDepartment(),"Computer Science");
            assertEquals(testSearch.get(i).getCourseCode(),"COMP 1010");
        }//for
        System.out.println("Finished searchEngineTest");
    }//testSearchEngine


    @Test
    public void testRemoveDuplicatedItem(){
        System.out.println("\nTesting testRemoveDuplicatedItem");
        List<Course> list2 = new ArrayList<Course>();
        //Adding the course 1 twice
        list2.add(course1);
        list2.add(course1);
        list2.add(course2);
        list2.add(course3);
        List<Course> list3 = SearchCourseList.removeDuplicateItem(list2);
        for(int i = 0; i < list3.size(); i++){
            assertEquals(list3.get(i),list.get(i));
        }
        System.out.println("Finished testRemoveDuplicatedItem");
    }
    
}//SearchCourseListTest
