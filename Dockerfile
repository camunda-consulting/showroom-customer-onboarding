FROM maven:3.8.5-openjdk-17-slim as builder

WORKDIR /usr/src/app
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn -B clean install


FROM eclipse-temurin:17
ENV TZ=Europe/Berlin

COPY --from=builder /usr/src/app/target/camunda-showcase-customer-onboarding.jar ./presales-demo-setup.jar

CMD java -jar ./presales-demo-setup.jar
