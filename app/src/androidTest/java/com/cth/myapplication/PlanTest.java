package com.cth.myapplication;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.cth.myapplication.presentation.HomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlanTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule = new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Test
    public void planTest1(){
        onView(withId(R.id.PlanButton)).perform(click());
        onView(withId(R.id.EditPlanButton)).perform(click());
        onView(withId(R.id.planAddButton)).perform(click());
        onView(withId(R.id.planTermSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("Winter"))).perform(click());
        onView(withId(R.id.planYearSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("2022"))).perform(click()); // 2022
        onView(withId(R.id.startButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.availableCourseList)).atPosition(0).perform(click()); // COMP 1010
        onData(anything()).inAdapterView(withId(R.id.availableCourseList)).atPosition(1).perform(click()); // COMP 1020
        onView(withId(R.id.finalizeButton)).perform(click());
        onView(withText("Yes!")).perform(click());
        onData(anything()).inAdapterView(withId(R.id.semesterList)).atPosition(0).onChildView(withId(R.id.semesterNameText)).check(matches(withText("Winter 2022")));
        onData(anything()).inAdapterView(withId(R.id.semesterList)).atPosition(0).onChildView(withId(R.id.removeSemesterButton)).perform(click());
    }
}
