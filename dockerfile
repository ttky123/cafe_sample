# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
ADD target/cafe_sample-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]