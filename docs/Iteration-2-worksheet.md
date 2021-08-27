# Iteration 2 Worksheet

## Paying off technical debt

There was an inadvertent and prudent technical debt in the `Course` Object that was payed off [here](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/commit/36093795b968a691ddb57251bf5385514508c25a#2bbc22f078d7aeb753fed8c1b6f0d3ebb785480c_44_43).<br>
We had made the `Term` Enum in a way that made it harder to use to compare different Terms and store the courses that were available in multiple terms in the database. It was inadvertent and prudent because we had not put too much thought into our database implementation involving courses available in multiple terms in Iteration 1.<br>
We fixed it by following the grader's remarks to change our implementation from using different booleans for each term to using just one integer that can represent all the combinations of terms, and using a bitwise AND to compare different terms.
<br>
Another technical debt was the use of `EnrolledStatus` enum that was meant to be used to mark courses as enrolled, unenrolled etc. This was also inadvertent and prudent for reasons similar to one listed above. As the HSQLDB was being implemented, we found out that it was better to stick with as primitive data types as possible to store in the DB. As a result, `EnrolledStatus` was removed in favour of a conventional String type. This debt was paid off in [this](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-10/umplanner-comp3350-a02-group10/-/commit/02d1006975ea6d3d4262b88fced4481cc8c85804) commit.
<br><br>

## SOLID
The SOLID violation found in A03 Group 10 is linked [here](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/47).
<br><br>

## Retrospective
The retrospective from iteration 1 failed to provide a change to the method of doing the project. Similar mistakes were made despite our efforts to not to do so.
<br><br>

## Design patterns
Other than the dependency injection, we are also using [Iterator design pattern](https://refactoring.guru/design-patterns/iterator).<br>
An Iterator is being used to iterate through a list of courses in the `searchEngine` method in [SearchCourseList.java](app/src/main/java/com/cth/myapplication/business/SearchCourseList.java) file.
<br><br>

## Iteration 1 feedback fixes
No issues opened by graders.