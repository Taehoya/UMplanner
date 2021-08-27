package com.cth.myapplication;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;


import com.cth.myapplication.application.Services;
import com.cth.myapplication.objects.Course;
import com.cth.myapplication.persistence.CoursePersistence;
import com.cth.myapplication.presentation.HomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule = new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Test
    public void courseNameTest1(){
        //Click Lookup course button
        onView(withId(R.id.LookUpCourseButton)).perform(click());
        //Search Linear Algebra and Matrix Analysis
        onView(withId(R.id.searchText)).perform(typeText("Linear Algebra and Matrix Analysis"));
        //Check that searched course is "Linear Algebra and Matrix Analysis"
        onView(withId(R.id.courseNameText)).check(matches(withText("Linear Algebra and Matrix Analysis")));
    }//searchTest


    @Test
    public void courseNameTest2(){
        //As a User, I need to be able to see course by course name
        //Click Lookup course button
        onView(withId(R.id.LookUpCourseButton)).perform(click());
        //Search COMP1010
        onView(withId(R.id.searchText)).perform(typeText("COMP1010"));
        //Check that searched course is comp1010
        onView(withId(R.id.courseNameText)).check(matches(withText("Introductory Computer Science 1")));
    }//searchTest
}
