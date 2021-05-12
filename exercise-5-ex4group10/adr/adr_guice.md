# Guice

Date: 2020-10-30

## Status

Accepted.

## Context
Running unit tests needs to be a fast action in order for developers to gain good insight into
changes as they are refactoring. Accordingly, relying on actual API calls to get data to test with and
parse is untenable. For this reason mock classes were written that act like the real APIs but only
return faked data. Guice provides a seamless way of choosing which implementation of an interface
is desired at any given time. 

## Decision
We are using Guice to inject either real of mock APIs into Observation.
Two different Guice `Module`s have been written. `ProductionDependencies` binds the Observation
web apis to real implementations of the `WindApi` and `WaveApi` classes respectively.
When the `App` is run, an `Injector` is built using ProductionDependencies. 
When the 'fullApiTest' task is run with gradle, ProductionDependencies are using in the tests
However if the regular 'test' task is run, a `MockedDependencies` module is instead created.
The means that the Observation will use `StubWaveApi` and `StubWindApi`. This will run quickly,
but accurately emulate the behaviour of the actual classes.


## Consequences
Using Guice means that it is easy to dynamically inject the desired API and improved processing time.
Hpwever, future users will need to understand when to modify the ProductionDependencies
if they create a new type of SofarApi that should be injected. In addition, the code can be a bit harder
to process because the usual constructor is not explicitly called in user code.

## Note
Guice injection happens in SofarApiIntegrationTest, ObservationTest and App/Driver.
The types injected are based on the context of the test.