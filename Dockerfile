FROM maven:3.8.4-openjdk-11 AS builder
# Tests do not work with cc. Hence, we do not copy them.
COPY ./src/main /src/main
COPY pom.xml ./

RUN mvn clean install

FROM openjdk:11
COPY --from=builder /target/camunda-showcase-customer-onboarding.jar ./
ENTRYPOINT exec java -jar camunda-showcase-customer-onboarding.jar