# Scala ProcessObs

## Status

Accepted

## Context

The ProcessObs.scala object needs to calculate the average weight in pounds of the whales in observations.csv
as quickly as possible, without making it difficult to change which file it is reading from.

## Decision

To ensure that the object can be pointed to read from different files, the line that creates the URL has not
been changed. This leaves the filename as a string and makes it very easy to change it.

For speed, the file passes over the input stream only once using a foldLeft call that takes two inputs: 
"tup" and "x". "tup" holds a tuple of the current average weight, and the number of rows that have been 
processed +1. "x" is the next input row. Before modifying tup's value, an if statement makes sure the
weight in the record is a valid weight (0 < weight < 50000).

## Consequences

The foldLeft call on the input stream is more complicated to understand - and therefore maintain - than a foreach call, 
or having two passes over the input stream (one which could track the total weight and one which could track how many 
records were stored in the file). However, avoiding for loops is a best practice in functional programming, and avoiding
two passes through the input stream has obvious speed advantages on large data sets.

Source for avoiding for loops best practice: http://behsys.com/mohsen/For-Loop-vs-Map-Functional-Programming-Recursive-Imperative-Implementation-Scala.html
