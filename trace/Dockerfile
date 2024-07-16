#FROM maven:3.9.6-eclipse-temurin-17 as build
#
#WORKDIR /app
#COPY ./source .
#
#ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.33.4/opentelemetry-javaagent.jar .
#
#RUN mvn -v
#RUN mvn install
#
#
#FROM eclipse-temurin:17-jre-alpine
#
#WORKDIR /app
#
#COPY --from=build /app/target/*.jar /app/run.jar
#COPY --from=build /app/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
#
#EXPOSE 3077
#ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "/app/run.jar"]

# HELP: execute mvn clean install in local machine first
FROM eclipse-temurin:17-jre-alpine

WORKDIR /source

COPY ./source/target/*.jar /app/run.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.33.4/opentelemetry-javaagent.jar /app

EXPOSE 3077
ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "/app/run.jar"]
