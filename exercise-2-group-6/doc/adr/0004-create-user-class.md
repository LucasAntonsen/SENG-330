# 4. Create User Class

Date: 2020-09-20

## Status

Accepted

## Context

A diverse group of people will use this application. Both scientists and ordinary citizens
will use it to record observations. There will be some scientists who will use the information 
from the observations for data analysis in order to gain insight to the behavioral patterns 
of the whales. 

## Decision

We decided to create a `User` parent class to represent a basic user. All classes representing users will inherit
from this class to define the minimum fields and methods required for a user. The two child classes of `User` are `Reporter`
and `Researcher`. The `Reporter` class represents the average user who creates the average observation. The `Researcher` 
represents the scientist who is responsible for creating or attaching `Whale` objects to specific `Observations`.

## Consequences

Ensures there are fields and methods common to all users. Child classes can provide any additional fields and methods appropriate for the level of user 
they are defining. 
