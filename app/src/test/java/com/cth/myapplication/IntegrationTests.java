package com.cth.myapplication;

import com.cth.myapplication.business.AccessCoursesIT;
import com.cth.myapplication.business.AccessSemesterIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessCoursesIT.class,
        AccessSemesterIT.class
})
public class IntegrationTests {
}
