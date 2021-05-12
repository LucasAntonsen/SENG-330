# Reflection in Unit tests
This isn't an adr, just to help you find the reflection test methods.

There are two private helper methods in SofarApi.class
`collectResponseFromStream` and `buildURL`. They are both tested using reflection
in the SofarApiTest class.

In `ObservationTest` two of Observation's private fields are access to ensure that the setter
works as intended.