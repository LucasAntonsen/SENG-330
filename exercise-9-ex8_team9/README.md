Please read thru the [overall exercises overview](https://github.com/SENG330/course/blob/master/exercises/Exercises.md).

# Exercise 9
In this exercise you will explore some of Java's functional programming updates (since JDK 8), as well as introduce yourselves to Scala and its approach to the same problems.

0. Prep. Consider the csv file of [whale observations](src/main/resources/observations.csv). Write code to parse the CSV into Whale Objects inside an ArrayList. You can store grid references as ints. Make good design choices for the other data fields. I've provided sample code for parsing Dates. Date parsing is another of those tricky edge cases (others are parsing emails and dealing with Unicode and character encoding). We have to be careful we understand where the dates come from (the locale: China? Germany? Canada?) and what calendar we are using, among other things. Move the sample code into a more sensible object structure than "Driver", which should just kickoff the application (alternately, forget the Driver and just use the test class to figure this out).

1.  Use method `removeIf` of `ArrayList` to remove all whales that are not Orcas. First, use an **anonymous** class as the explicit argument to `removeIf`. Once this works, replace the anonymous class with a **lambda** expression written as a block of statements, and with type parameter. Finally, transform your lambda expression once again so that it is defined as an **expression**, has no type parameter or parentheses. *Submit each change as a separate commit.*
  
2. Use `java.util.function.BiPredicate` to implement a class that will take in two Whale objects and return the one which is heavier. Use this to sort the list of Whales.
 
3. Use a `flatMap` operation to count the number of whales found before 2007-06-01. NB: this will be easier if you store Whale sightings as `Date` objects.
     
4. Use map and reduce to convert the Whales to a single **average** mass of all observed whales in pounds for the US Coast Guard (1 pound = 2.2 kg). Currently all weights are in kilograms. Then use a single `reduce` operation to obtain the largest whale in the collection.
   
5. **Scala**. Extend the simple Scala function in /src/scala/ProcessObs.scala that process the CSV file above, so that it takes the stream of entries (a `HashMap` of header:value) and computes the average mass as before. Use the [Scala reference](https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/Stream.html).

Write Java tests for all the Java components. 

## Learning Objectives
 - functional programming
 - Scala language elements
 - function interfaces
 - Java streams
 - organizing a library application

## Deliverables
* The source code implementing the above.
* The included markdown file called "solutions.md" which points to the relevant lines of code. You *must* use [Markdown links](https://daringfireball.net/projects/markdown/syntax#link) to point to the commit and line number / file resolving it. 
* Each group member must make a commit that solves one of the questions. E.g., for the 8 questions, all 5 or 6 people should be committing changes to resolve them. The prep counts as one question.
* Tests that show the questions being answered.

## Tips
* Figure out data parsing for the assignment and get the setup done by Wednesday. This is the bottleneck.
* It probably doesn't make sense to create a separate class for each answer. 
* The CSV file is tiny, so you can figure out the answer manually (e.g., the largest whale). Use this to create tests for each question. You can easily create the tests before anyone has written code.
 
## Due
- We will mark the last commit made before **Nov 27 at 11:59pm**. If that last commit was a mess, let us know. Make sure your code compiles and all tests pass!
- By midnight Nov 29, submit your team peer review form via the webapp (link omitted to prevent spam). *You must add text that explains your rating if it is not Very Good or Excellent*.
