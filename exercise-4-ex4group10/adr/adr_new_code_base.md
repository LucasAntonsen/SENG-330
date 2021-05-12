# Picking our New Code Base

Date: 2020-10-16

## Status

This decision has been implemented, and the code base has been refactored accordingly.

## Context

One task of the fourth assignment was to pick our new code base to work from. We had 5 choices:
  
  1. Alec's code base: this code base comprised the 3 basic classes we all had to implement with an additional environmental state class (Sighting State) that serves as a class to record the environmental conditions observed during the sighting. Alec's code base used a set to store whales and the observations recorded where completely immutable. This was the case because it was assumed that any recorded observation should remain the same to conserve data integrity.
  2. Meghan and Nathaniel's code base: this code base comprised the 3 basic classes we all had to implement with an additional conditions class (similar to sighting state) and a coordinates class. This code base used an int to store the number of whales, but did not allow for multiple whale objects to be created for a whale observation. This class used a radius around coordinates to search for observations within that radius.
  3. Ashwin's code base: this code base comprised the 3 basic classes we all had to implement with the addition of a coordinates class. This code was unfinished but used a unique line sort to sort coordinates.
  4. Lucas's code base: this code base comprised the 3 basic classes we all had to implement with the addition of helper classes and repository classes to act like a database for the objects created in the observation. This code had no link between observation and whale objects and stored these in their respective repositories.
  5. Mek's code base: this code base comprised the 3 basic classes we all had to implement with the addition of helper classes and anonymous comparator classes. The observations where completly immutable by not including setters (could've done the same thing with final) and this group used plantUML for their diagramming.
  
Ignoring the commonalities between the code bases, we needed to decide which aspects of which code base we wanted to integrate, and which code would be the least work to integrate and fix based on observations during our meeting.

## Decision

The team decided on three key things our new code base would need. We decided on:

  1. Using a set of whales (or some similar representation) for the whale objects that where created in our observations.
  2. Including a coordinates class and use a coordinates object within our observations class
  3. Using better encapsulation where appropriate.
  
Based on these 3 key decisions, we decided to use Alec's code base and to integrate the coordinates class from Meghan and Nathaniel's code base. It came down to choosing between Meghan and Nathaniel's code base and Alec's code base and we picked Alec's because it required the least amount of re-work (the other code base needed test refactoring) and was the simplest implementation of the assignment specifications. We used the `Coordinates` class from Meghan and Nathaniel's code base as Ashwin's code base's implementation of the class was unfinished and likely required re-work.

## Consequences

Our decision assured that our re-work would be minimal and we would get everything we wanted in one code base. Because of this we had to refactor Alec's code base a little bit by integrating the coordinates class and it's accompanying test cases. It also worth noting that because the observation class is immutable, we would have to have future front end validations to make sure the data being passed in isn't noisy or invalid.
