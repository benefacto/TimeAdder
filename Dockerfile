# Use the official OpenJDK 20 image as the base image
FROM openjdk:20-jdk-slim

# Set the working directory in the container to /app
WORKDIR /app

# Copy the local project files to the container
COPY . .

# Compile the project
RUN ./gradlew build

# Command to run the application
ENTRYPOINT ["./gradlew"]
