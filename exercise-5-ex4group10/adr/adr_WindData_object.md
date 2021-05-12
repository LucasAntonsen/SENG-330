# WindData Object

Date: 2020-10-29

## Status

Accepted.

## Context
Similar to in adr_WaveData_object, the Sofar API also provides a variety of weather qualities given a specific location.
Wind data, such as the 10 meter wind speed, also is useful for understanding the optimum weather conditions for whale
habitation. Using the API, JSON data enables access to these specific qualities and requires storage in a java object.

## Decision
Create a `WindData` object with the following fields:

`physicalUnit` - Unit of wind speed (String)  
`value` - Magnitude of wind speed (double)  
`timestamp` - Time of measurement recording (String)

## Consequences
JSON data regarding Magnitude 10 Meter Wind Speed is easy to parse and store in a `WindData` object.