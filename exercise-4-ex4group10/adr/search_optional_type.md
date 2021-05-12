# Optional Type in searchByLocation()

Date: 2020-10-16

## Status

Accepted.

## Context
Searching by location is a challenging task due to the specificity of the target `Observation`. When searching by location, even a small difference in the target will result in
a search that yields no results. Instead of returning null from the method, Java's `Optional` was considered to ensure that the client considers the likely outcome of no valid data. Dealing with methods that return null can cause many issues for users at runtime.

## Decision

The `searchByLocation()` method was refactored to use `Optional`. This will help ensure that clients are aware of a non result.

## Consequences

`NullPointerExceptions` should be less common. While there may be slightly more overhead, this should prevent issues. It may also be worth adding additional filter based functionality to searching by location.
