# 6. create pod class

Date: 2020-09-22

## Status

Accepted

## Context

Whales are often members of pods. Being a scientific system, the concept of a pod should be added so that users of the
system can track pods and the whales that are in them.

## Decision

We created a `Pod` class that records the name of the Pod and contains a list of the whales that are a member of the pod.
Whales are recorded as being a member of a pod using an instance of `Repository<Whale>` discussed in [ADR 5](0005-create-repository-interface.md).

## Consequences

This decision allows the system to track more information regarding whales. Future functionality that may require 
information regarding a pod has a component to interact with.
