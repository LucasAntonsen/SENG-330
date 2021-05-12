

# Java's functional programming and Scala


## Status

accepted

## Context

In this assignment, we use Java's functional programming and Scala.
The founder of Java has stated that Java is moving closer to Scala after every new version of Java is released.
Scala itself is built from the JVM, so it is considered a JVM language. 
Scala allows programmers to program in a functional programming paradigm. 
Scala is extremely effective when working with Big Data and is used 
extensively with Apache Spark and Apache Hadoop.
Modern ML systems are also intrinsically tied to modern Big Data systems.

## Decision

In this assignment our team fulfilled the Java functional programming requirements and Scala requirements, trying to utilize the functional programming paradigm as effectively as possible. To go above and beyond our team decided to test our code using Big Data. 
To test the functional code with Big Data, the codebase holds a class called [GenerateData](https://github.com/SENG330/exercise-9-ex8_team9/blob/main/src/main/java/ca/uvic/seng330/ex9/generate/GenerateData.java) which generates a large amount of test data. With the GenerateData class, the tests can generate test files with millions of rows. By testing our code on millions of rows, the code is being tested as if it was in a production environment ([BenchmarkTest](https://github.com/SENG330/exercise-9-ex8_team9/blob/main/src/test/java/ca/uvic/seng330/ex9/BenchmarkTest.java)). Systems that use BigData have very different requirements than systems that don't use BigData. Languages such as NoSQL and specialized databases and data environments are being created to handle the large amounts of data. 
The large data input files will allow our features to be benchmarked with different amounts of data. Speed and memory tests are an effective way of measuring code that gives an objective measure. 

## Consequences

The consequences of Benchmarking our code with large input files include a longer time to run tests.
Ways to cache the large input files locally that are created are needed because we don't want to re-create the files every time a test is run, and we also do not want to push the large test files to Github. A positive consequence is that it allows us to show that our code works correctly with a larger data set.
