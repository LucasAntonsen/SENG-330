# 2. Create Whale Class

Date: 2020-09-20

## Status

Accepted

## Context

There are several types of whales that live in the ocean near Vancouver Island.
We need an abstraction of a whale in our system that allows researchers to track and organize all known whales.

## Decision
We decided to create a `Whale` class to generically represent a whale. The observer will
then specify the relevant identification details such as `species`, `gender`, etc. There are some 
fields, such as `whaleId`, `gender`, and `pod`, that the average observer will not likely be able to fill out.

## Consequences

A scientist in the field making observations will likely be able to fill out all the fields accurately.
Since the average observer will not likely know the answers to every field, they may choose
to leave some blank. In order to avoid having null fields, the rest of the fields will need 
to be initialized to some default values. 

`Whale` class will also need a copy constructor to prevent someone from unintentionally editing the
contents of all objects.
