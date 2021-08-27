# Iteration 1

## Adding a feature

We were able to add one feature to the project: Look up courses.<br>
To implement the feature we first built a 3-tier project structure, followed by building domin specific object (Course) required for this feature. After that, the UI was built in the presentation layer and tested using a few manually added courses, which placed in a fake database in the persistence layer. For communication between the UI and data, a piece of logic (AccessCourses) was added. While the feature was being implemented, a script was written to fetch some "real" course data. This script is not part of the project yet but some data scraped from aurora has been added to the persistence stub. To finish it off, more UI was added for the ability to filter by department and term, and a search functionality was implemented in the UI along with the logic backing it up.
<br><br>
**Links:**<br>
Feature: #18<br>
User stories: #36, #37, #40, #43<br>
Tests: All tests are available [here](./app/src/test/java/com.cth.myapplication/AllTests.java)<br>
Merge commit: [link](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/commit/ce0d472e26fc53893a51052bf24a2783fdbf5105)<br>
<br>


## Branching Strategy

Our branching strategy is located [here](./BranchingStrategy.md).<br>
<br>

## SOLID violation
SOLID violation found in Group 11's project [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-11/In-Need/-/issues/24).
<br>
<br>

## Agile Planning
Despite us planning to release all 3 features expected in for this iteration, we were only able to finish the implementation of one feature (Look up Courses) and have decided to push back the remaining two features to iteration 2. Work has been started on these features but we did not manage to finish them in time for iteration 1.