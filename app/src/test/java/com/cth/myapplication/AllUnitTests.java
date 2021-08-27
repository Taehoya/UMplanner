package com.cth.myapplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cth.myapplication.business.AccessCourseTest;
import com.cth.myapplication.business.AccessSemesterMockTest;
import com.cth.myapplication.business.AccessSemesterTest;
import com.cth.myapplication.business.SearchCourseListTest;
import com.cth.myapplication.objects.CourseTest;
import com.cth.myapplication.objects.SemesterTest;
import com.cth.myapplication.objects.TermTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CourseTest.class,
        TermTest.class,
        SemesterTest.class,
        AccessCourseTest.class,
        AccessSemesterTest.class,
        AccessSemesterMockTest.class,
        SearchCourseListTest.class
})
public class AllUnitTests
{

}
