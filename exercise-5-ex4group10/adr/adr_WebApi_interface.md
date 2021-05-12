# WebApi Interface

Date: 2020-10-26

## Status

Accepted.

## Context
One of the most important considerations for using external services is how to mock them, to enable rapid unit testing
of other parts of the project. Accessing the web is slow and unreliable, so it is advantageous to use mocks to skip these
steps unless critically necessary. The easiest way to enable mocking is to use Java's natural polymorphism.


## Decision
For this reason, a WebApi interface was designed to define how users will interact with any web service. There are five
methods in the interface. getData, query, onReceive, onError and, getError. The getData method returns an Optional that
may include data from the most recent API call. Optional was chosen because there are many states that correspond with
invalid or non existent data. In addition, the interface was designed to support asynchronous requests, if that is
how subclasses are implemented.
The query method actually invokes a call to the web service, however that may occur. It is void once again to allow for
asynchronous processes. onReceive is called if the server returns data without an error code. Clients may choose to use
the data for whatever the purpose may be. onError will be called in place of onReceive if there is an error code response
from the server. The class should generate and store an error message if onError is called. This error message will be
available through the getError method.



## Consequences
Using an interface allows for easy dependency injection. In places where a real API exists, mock subclasses can implement
the methods with predetermined behaviours. The API leaves the option open for APIs to use blocking or asynchronous
implementations.