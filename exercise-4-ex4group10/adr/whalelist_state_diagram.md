# WhaleList State Diagram

Date: 2020-10-16

## Status

Accepted

## Context

A state diagram demonstrates key conditions of a system during operation over a discrete period.
To understand the key processes and conditions of our program over time, a state diagram is necessary.
WhaleList provides an excellent choice for a stateful object to diagram.

## Decision

Create WhaleList state diagram.

There are 3 states and 7 transitions between.

**States:**

1. **Empty WhaleList** - WhaleList with empty `ArrayList<Whale>`. Demonstrates empty state of WhaleList.

2. **Unsorted WhaleList** - WhaleList with one whale element or greater in `ArrayList<Whale>` that is not sorted. 
Demonstrates unsorted state of WhaleList.

3. **Sorted By Comparator** - WhaleList with one whale element or greater in `ArrayList<Whale>` that has been
sorted. Demonstrates multiple sorted states of WhaleList condensed into one state.

_Transitions:_

1. _WhaleList()_ - Creates WhaleList with empty `ArrayList<Whale>`. Transitions origin to **Empty WhaleList** state.

2. _WhaleList(other)_ - Copies `whales` from other WhaleList to new WhaleList's `ArrayList<Whale>`. Transitions origin
to **Unsorted WhaleList** state.

3. _Add(whale)_ - Adds whale object to WhaleList's `ArrayList<Whale>`. Transitions **Empty WhaleList** and 
**Sorted By Comparator** states to **Unsorted WhaleList** state.

4. _remove(whale) [size > 1]_ - Removes whale object from WhaleList's `ArrayList<Whale>`. Transition maintains
**Sorted By Comparator** and **Unsorted WhaleList** states since removal will not affect unsorted/sorted condition.

5. _clear()_ - Removes all whale objects from WhaleList's `ArrayList<Whale>`. Transitions **Sorted By Comparator**
and **Unsorted WhaleList** states to **Empty WhaleList** state.

6. _remove(whale) [size == 1]_ - Removes last whale object from WhaleList's `ArrayList<Whale>`. Transitions 
**Sorted By Comparator** and **Unsorted WhaleList** states to **Empty WhaleList** state.

7. _sortBy(comparator)_ - Sorts WhaleList's `ArrayList<Whale>` by given `Comparator<whale>`. Transitions 
**Unsorted WhaleList** to **Sorted By Comparator** state. Maintains **Sorted By Comparator** state.

## Consequences

WhaleList's various conditions through execution are clearly visible.
