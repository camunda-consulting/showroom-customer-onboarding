#base java 8
FROM openjdk:8-jdk-alpine
# necessary for apk installation
USER root
#install node
RUN apk add --update nodejs nodejs-npm

#configure timezone
RUN apk add tzdata
RUN cp /usr/share/zoneinfo/Europe/Berlin /etc/localtime
ENV TZ="Europe/Berlin"
#define vol
VOLUME /tmp

#copy artifacts
COPY package.json .
COPY datatransfer.sh .
COPY dataupload-app.js .
COPY target/camunda-showcase-customer-onboarding.jar camunda-showcase-customer-onboarding.jar

#install node dependencies
RUN npm install @google-cloud/storage --save

ENV LANG=en_US.utf8

ENTRYPOINT ["sh", "datatransfer.sh"]
