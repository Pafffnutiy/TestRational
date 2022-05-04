#!/bin/bash
javac -d . src/main/java/Rational.java 
javac -d . -cp junit-4.13.2.jar:. src/test/java/Tests.java
java -cp junit-4.13.2.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore Tests
