#!/bin/bash

# Start the first process
echo "starting applications"
JAVA_OPTS=""
DOCKER_JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# uploads if should stay active after simulation
if [ $transfertype ]
then
  echo $transfertype
  if [ $stayActiveAfterSim = false ]
  then
    java ${JAVA_OPTS} -Xmx2048m ${DOCKER_JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ./camunda-showcase-customer-onboarding.jar  -D && sleep 10 && node ./dataupload-app.js -D
  else
    node ./dataupload-app.js -D && sleep 10 && java ${JAVA_OPTS} ${DOCKER_JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ./camunda-showcase-customer-onboarding.jar  -D
  fi
else
  java ${JAVA_OPTS} -Xmx2048m ${DOCKER_JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ./camunda-showcase-customer-onboarding.jar
fi