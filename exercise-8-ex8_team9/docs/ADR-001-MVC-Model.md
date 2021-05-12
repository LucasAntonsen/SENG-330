# ADR 001: Data Model

## Status

Accepted.

## Context

In Exercise 8 we had to set up a basic MVC for the data in a whale observation application. 
We anticipate that this model will be used in Exercise 9, so a flexible model is key as 
we do not yet know what Exercise 9 entails.

## Decision

The data Model we are using is based on accepting Observations as a stream and both storing these
Observations (in an ArrayList) and tracking certain values that will be displayed in a report screen.

The View logic is fairly straight forward and is set up to take input from the user and pass it to the 
Controller. When the Controller receives values, it creates a temporary Observation object. This Observation 
is changed each time the input is updated but is sent to the data model only when the submit button is clicked.

We implemented the Observer pattern to allow the date model to notify the relevant GUI components 
of updates when required.

## Consequences

We believe this framework will be able to be easily adapted to whatever functionality is required 
in Exercise 9.

The data model will make it fairly straight forward to implement things like reviewing past Observations
while allowing a summary or report to be displayed in constant time. However, storing past 
Observations in main memory will cause issues in a production system. Changing this to store these 
Observations on disk would need to happen before this system could go into a production environment.
