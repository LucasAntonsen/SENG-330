Please read thru the [overall exercises overview](https://github.com/SENG330/course/blob/master/exercises/Exercises.md).

## Exercise 3
1. Building on the work from Ex2, correct the code based on the TA feedback. Note: you do not need to amend the ADR or the diagram. You will want to copy in your code from Ex2 to this repository.
2. Expand your code to show how we can:
    1. search through a list of whale observations, and 
    2. sort that list. 
3. To do that, think about what a Whale observation is, and how you might want to search/sort these observations. 
4. Create a `Comparator` (page 48) for whales. In your ADR document why the approach you chose is preferable over the
 other 2 approaches discussed in the book.
5. Make a `Collection` of `Whale` `Observation`s that is `Comparable`.
3. Implement two different strategies for searching (using `Collection.binarySearch()`) and two different strategies for sorting (using `Collection.sort()`). Cf. text page 54. Your ADR should address the design questions raised on page 55.
3. Draw a Class diagram to show the structure of your solution. The Class diagram should motivate the answers/decisions raised in your ADR.
5. Use tests to show how a) the strategies can be altered with little modification to the rest of the code; b) that the search and sort work. You will need to create test data - use Junit's `@Before` annotation to instantiate the test data (see https://www.baeldung.com/junit-before-beforeclass-beforeeach-beforeall).

### Notes:
- the search/sort happen in memory. In practice this might be dangerous, if we had a huge number of objects and
 limited RAM.
 - Do NOT write your own search/sort algorithms. There are plenty in the Java standard library. Except for undergrad
  (225/226/320 etc), it would be a rare thing for a programmer to improve on the JDK implementations.
 
### Learning Objectives
- Fix code following the TA's code review.
- Learn to draw a basic class diagram.
- Use Iterator, strategy patterns in context.
- Use a Function object

### Deliverables

* An ADR capturing the class diagram and explaining the rationale. Your ADR should refer to concepts in Chapter 3.
* Source code implementing the class diagram.
* Test cases that capture the normal and error cases for your algorithms (e.g., sorting an Empty collection).

Capture your important decisions in the ADR template.

## Due
- We will mark the last commit made before **October 9 at 11:59pm**. If that last commit was a mess, let us know. Make sure your code compiles!
- By midnight Oct 11, submit your team peer review form (link to follow). We will discuss the peer rating form in class.
