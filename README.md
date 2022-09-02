# Kotlin

Here's a refactored version of the code by using the StringBuilder class. After some debugging, I found out that tests were failing because of a carriage return on windows.
The JDK was adding \r into the strings every time there was a \n. So replicating that into the expected results fixed the tests.
