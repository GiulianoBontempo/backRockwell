# Use an official Java runtime as a parent image
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/Rockwell-CRUD-0.0.1-SNAPSHOT.jar /app/Rockwell-CRUD-0.0.1-SNAPSHOT.jar

# Specify the command to run when the container starts
CMD ["java", "-jar", "Rockwell-CRUD-0.0.1-SNAPSHOT.jar"]