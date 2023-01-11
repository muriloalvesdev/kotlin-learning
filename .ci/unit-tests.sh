#!/bin/bash
export MAVEN_OPTS="-Xmx1G -Xms128m"
mvn clean test -Dgroups="unit-tests" -Dsurefire.useFile=false