# Choosing Our API

Date: 2020-10-30

## Status

Accepted.

## Context
Before beginning development we had to choose a web service to query to populate our program. We had the choice between the APIs supplied in our assignment specification, or we could find our own that matched our specific needs. Our decision came down to two APIs, namely https://stormglass.io/ and https://www.sofarocean.com/  

## Decision
We decided to use sofar over stormglass. We decided sofar was better as it had more relevant data to our current application, it had 100,000 querries free compared to the 50 daily queries from stormglass, and it had better documentation.

## Consequences
Because we didn't use stormglass we lost some potential good data we could have used in our application. Although we lost this data, using sofar allowed us more api calls and it had better documentation, making our implementation less complex then it would have been when using stormglass.
