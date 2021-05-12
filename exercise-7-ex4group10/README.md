Please read thru the [overall exercises overview](https://github.com/SENG330/course/blob/master/exercises/Exercises.md).

# Exercise 7
In this exercise you will explore the benefits and limitations of inheritance-flavoured OOP and concurrency in Java.

2. I've created a shared data structure, `Points`, in the src folder. Use Java's concurrency API to implement access to this shared data structure. 
	* Change the code in `Driver.java` so that the 3 clients run in three threads incrementing the points total and displaying the total points. Confirm that the results are stochastic, i.e., they change each time the code is run.
	* Then change this code to fix this with the concurrency API as discussed in videos/lectures. The result should be that the correct total is printed independent of thread execution. 
3. Examine the **concurrency approach** used in either Scala, Erlang, or Go, and write a 1 page markdown file describing the pros and cons of *ONE* concurrency primitive in that language vs the Java equivalent. Remember to properly cite your references used (e.g., the Scala website, blog posts, academic papers).
4. Run [PMD, a static analyzer,](https://pmd.github.io/pmd-6.26.0/pmd_rules_java.html) and **find 3 code smells** in your Ex 5 source code. If your code is already super clean, make some smelly code to get the detector to work.
	- I have added PMD to Gradle, so it should run the task `gradle pmdMain` for you (isn't Gradle wonderful?!). You can either copy this into your Ex 5 repo, or copy the code into this one. 
	- Document what smells you found.
	- Now fix them using refactoring (other than `rename`). A good list of possible refactorings is https://refactoring.guru/refactoring. 
	- Create a document that shows before and after code snippets. Do this by using commits, in which commit X-1 shows the bad smell, and commit X shows the refactored code. Your submission needs to record at least 6 commit SHA-1 hashes (the first 8-10 characters are fine): 3 before and 3 after.
	- Link to the line of code in which the smell occurs, like this: https://github.com/SENG330/ex5_starter/commit/83d5a6e8866432bf9e7453b87e0e97996db778ca#diff-9c1be2884a04419ef1ad1b7f690b2b48d230027d27ca6b09efc23e0ecb04aa91R14

## Learning Objectives
- types and inheritance
- concurrency and its mechanisms in Java and other languages
- static analysis tools and refactoring 

## Deliverables
* No ADR necessary unless specific design decisions should be explained to the TA.
* the 1 page (750 words max) compare and contrast file. I've created the skeleton for this file already as `contrast.md`.
* a markdown file with Github links to the 6 refactoring related commits (use `refactoring.md`).
* a markdown file (`concurrency.md`) with the 2 concurrency related commits.

## Tips
* You can link to [specific commits in Github files](https://docs.github.com/en/free-pro-team@latest/github/writing-on-github/autolinked-references-and-urls) by adding the SHA1 hash id of the commit. 
 
## Due
- We will mark the last commit made before **Nov 8 at 11:59pm**. If that last commit was a mess, let us know. Make sure your code compiles!
- By midnight Nov 11, submit your team peer review form via the webapp (link omitted to prevent spam).
