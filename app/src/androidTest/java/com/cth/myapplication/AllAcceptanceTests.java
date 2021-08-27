package com.cth.myapplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlanTest.class,
        CompletedTest.class,
        SearchTest.class
})

public class AllAcceptanceTests {
}
