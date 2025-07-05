FROM openjdk:21-jdk-slim

COPY target/eracon.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=testing"]