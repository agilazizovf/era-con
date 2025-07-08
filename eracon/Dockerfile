FROM openjdk:21-jdk-slim

COPY target/eracon.jar app.jar

# Varsayılan olaraq dev profili təyin edirik, amma sonradan overwrite oluna bilər
ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java", "-jar", "app.jar"]