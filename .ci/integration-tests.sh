#!/bin/bash
export MAVEN_OPTS="-Xmx1G -Xms128m"
mvn package -Dgroups="integration-tests"