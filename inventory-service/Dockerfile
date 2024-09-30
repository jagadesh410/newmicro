#this component is used to define Images in Dockerfile
FROM openjdk:21-jdk

#it is used to create a path inside a container and we will directly go to that path.
WORKDIR /app

#this will copy the JAR file to the container
COPY target/inventory-service-0.0.1-SNAPSHOT.jar /app/inventory-service.jar

#it will expose the application port
EXPOSE 8081

#it is used to perform a command while creating the container
ENTRYPOINT ["java", "-jar", "/app/inventory-service.jar"]