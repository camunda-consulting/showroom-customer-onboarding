#!/bin/bash

# Start the first process
JAVA_OPTS=""
DOCKER_JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

if [ $mode = "demo" ]
then
  java ${JAVA_OPTS} -Xmx2048m ${DOCKER_JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ./camunda-showcase-customer-onboarding.jar  -D && sleep 10 && node ./datatransfer.js -D
elif [ $mode = "run" ]
then
  node ./datatransfer.js -D && sleep 10 && exec java ${JAVA_OPTS} ${DOCKER_JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ./camunda-showcase-customer-onboarding.jar  -D
else
  exec java ${JAVA_OPTS} -Xmx2048m ${DOCKER_JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ./camunda-showcase-customer-onboarding.jar
fi