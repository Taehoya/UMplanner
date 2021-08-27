# Iteration 3 Worksheet

## What technical debt has been cleaned up
For iteration 3 no technical debt has been cleaned up

<br>

## What technical debt did you leave?

Implementing a back button was impossible since all the activities did not have a toolbar implemented and to include a back button would mean that we have to manually write code for it or add a toolbar to all activities.
We classify this debt as a prudent deliberate technical debt.<br>
There is also a nested lists issue where a list of semesters contains a list of courses. The outer list can be scrolled but the inner list cannot be scrolled.
They were both implemented as listViews which is the reason why it does not scroll. A possible solution is to implement one of them as a recyclerView. This debt is classified as inadvertent technical debt.
<br><br>

## Discuss a Feature or User Story that was cut/re-prioritized
Login feature<br>
We changed the login feature priority in iteration 3 because the project had been started and came on without keeping the feature in mind. Also because there are no separate copies for course data that will account for each user therefore incompatible at this stage to implement in iteration 3<br>
We also cut sharing schedule feature for now to finish implementing what was important for the application i.e., Completed course feature<br>
The user story for the login feature is located [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/issues/91)<br>
The user story for the sharing schedule feature is located [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/issues/16)

<br><br>

## Acceptance test/end-to-end
We wrote tests for testing the Search, Plan and Completed semesters features. We had to sleep the thread just before pressing the back button to make sure the tests ran properly. All acceptence tests are available [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/tree/master/app/src/androidTest/java/com/cth/myapplication/AllAcceptanceTests.java).

<br><br>

## Acceptance test, untestable
It is not possible to test the courses inside each semester on any SemesterList due to the views having a nested listView.

<br><br>

## Velocity/teamwork

The estimates got worse but actual work done stayed consistent throughout the course since three features were estimated to be
completed by the end of first two iterations but a total of one feature was completed consistently over the iterations.<br>
The estimates could not be met due to inability to produce quality code, i.e., in both iteration 1 and 2 the codes for some features even though completed,
had to be eliminated as they would have brought in a lot of technical debts. This led to a few people being able to bring in quality work done and hence the slow progress over the iterations.<br>
Evidence of the estimates could be seen in iteration 3 where some user stories that were estimated to be completed but had to be cut and pushed to future iteration<br>
Links<br>
[Velocity graph](./docs/Velocity_Graph.png)<br>
[Reminder Feature](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/issues/83)<br>
[Login feature](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/issues/91)<br>
[Sharing Schedule](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/issues/16)
<br><br>
