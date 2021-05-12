# 2. Observation State Diagram

Date: 2020-10-15

## Status

Approved

## Context

Observation State diagrams represent a dynamic view of observation-related components of our whale monitoring system.
With this diagram, important abstraction about the state of observation is captured for self-
evaluating the impact of design decision.


## Decision

The observation state diagram represents <b>3</b> main abstract states (Incomplete Observation,
Setting, and Updated Observation) and
<b>5</b> composite abstract states within the "Setting" state. The "Incomplete Observation" state is annotated
as the <i>start state</i> where an observation object exist with only reporter and time
representing it. In this state, both reporter and time must not be null due to the design decision
to avoid introducing potential issues.

From the "Incomplete Observation", transitioning to the "Setting" state 
implies calling one of the setters in observation:

````
setCoordinates(Coordinates c)
setNote(String note)
setState(SightingState state)
setHeadingDirection(Direction headingDirection)

addWhaleToPod(final Whale whale)
addWhalesToPod(Collection<? extends Whale> whales)
addWhalesToPod(Whale ... whale)
````

To keep the number of states and transitions minimized, the transition from the above method calls 
is simply labeled as "object update", which refers to entering one of the composite states in "Setting".

Within the composite states of "Settings", the objects that are member of observations
are either null or exist. If the object is null, its state will be labeled as "Empty ..." and can only
exist if it's set with non-null reference (e.g. by calling `observation.setState(new SightingState())`). 
If the object already exists, the name of its state begins with "With ..." and the transition from "Empty ..." state to 
"With..." state is called "add" to specify setting with non-null value. Objects in the "With ..." state can transition to "Empty ..."
state when "reset" (e.g. `observation.setState(null)`) or update its fields (hence "update" transition"). 
Although allowed, the absence of self-transition in "Empty ..." state is excluded as it's redundant and meaningless.

In the composite state "Initialized Pod", pod is constructed in the constructor of observation as an empty set so
its state cannot be null. Only existing whale objects can be added to pod and not removed, so "Initialized Pod" 
only have two possible states. The composite states have "finish" transitions to "Updated Observation" as a result
actions in the "Setting" state.

Although the transition from "Updated Observation" to
"Incomplete Observation" can be seen as "absence" due to missing a transition link, 
we chose to exclude this transition to simplify the meaning of "Empty Observation", that is 
to say that observation has never been updated at all. If the observation has been in a setting state once,
it will be considered to be in "Updated Observation" and self-transition is maintained.


## Consequences

To identify potential issues of `Observation` from the initialization state ("Incomplete Observation") to
updating state ("Setting").
