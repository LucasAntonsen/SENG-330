# 3. Create Observation Class

Date: 2020-09-20

## Status

Accepted

## Context

This whale monitoring tool will be used by people with a wide range of professional and 
educational backgrounds. Due to the diversity of the users and the challenges involved in 
observing large sea creatures, some observers may be able to make more detailed observations
than others. 

## Decision

Create a parent class called `Observation` that all classes representing observations must inherit from.

## Consequences

By creating this class, it makes it easier to create different types of `Observation` 
classes. Currently, there are only two types of observations proposed: `SimpleObservation` (no location)
and `LocationObservation`(location recorded). However, by creating this class, it provides 
a base for any additional types of observations that we want to add to the design later on.
