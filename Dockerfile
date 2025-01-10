FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD /target/orgs-0.0.1-SNAPSHOT.jar orgs.jar
ENTRYPOINT ["java", "-jar", "orgs.jar"]