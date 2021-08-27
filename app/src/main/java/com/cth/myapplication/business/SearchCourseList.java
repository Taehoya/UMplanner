package com.cth.myapplication.business;

import com.cth.myapplication.objects.Course;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchCourseList {
    //This methods get the argument as list and string
    //and then return the list of courses that contains that string
    public static List<Course> searchEngine(List<Course> list,String userInput){
        List<Course> filteredCourse = new ArrayList<Course>();
        Iterator<Course> it = list.iterator();
        while(it.hasNext()){
            Course course = it.next();
            if(course.getCourseName().toLowerCase().contains(userInput.toLowerCase())){
                filteredCourse.add(course);
            }//if
        }
        //get the code with course code
        //need to find that use type as comp/math that is in the code
        it = list.iterator();
        while (it.hasNext()){
            Course course = it.next();
            if(course.getCourseCode().toLowerCase().contains(userInput.toLowerCase())) {
                filteredCourse.add(course);
            }
        }
        return removeDuplicateItem(filteredCourse);
    }//searchEngine

    //Return the list that has no duplicated item helper method
    public static List<Course> removeDuplicateItem(List<Course> list){
        ArrayList<Course> returnList = new ArrayList<>();
        for(int i = 0; i<list.size(); i++){
            //Just add the item that returnList does not have
            if(!returnList.contains(list.get(i))){
                returnList.add(list.get(i));
            }//if
        }//for
        return returnList;
    }//removeDuplicateItem
}//searchItem
