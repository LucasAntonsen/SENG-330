# WaveData Object

Date: 2020-10-28

## Status

Accepted.

## Context
The Sofar API provides a variety of ocean qualities given a specific location. Wave data, such as mean direction and
significant wave height is useful for understanding the optimum ocean conditions for whale habitation. Using the API,
JSON data enables access to these specific qualities and requires storage in a java object.

## Decision
Create a `WaveData` object with the following fields:

`waveHeightUnit` - Unit of wave height (String)  
`waveDirectionUnit` - Unit of wave direction (String)  
`waveHeight` - Wave height measurement (double)  
`waveDirection` - Wave direction measurement (double)  
`timestamp` - Time of measurement recording (String)

The JSON keys for Significant Wave Height and Mean Direction are identical, so they cannot be mapped directly to one 
object. Instead, a `WaveDataHelper` object copies the qualities into two temporary objects, and then copes their fields to
`WaveData` to preserve the unique data and prevent overlap.

## Consequences
JSON data regarding Significant Wave Height and Mean Direction is easy to parse and store in a `WaveData` object.