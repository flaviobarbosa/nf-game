## First stage: build the application
#FROM maven:3.9.9-eclipse-temurin-17 AS build
#COPY . /app
#WORKDIR /app
#RUN mvn package -DskipTests
#
## Second stage: create image
#FROM openjdk:17-slim
#COPY --from=build /app/target/neogame-0.0.1-SNAPSHOT.jar /app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]

# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy project files
COPY . .

# Grant permission to the Maven wrapper
RUN chmod +x mvnw

# Default command (can be overridden)
CMD ["./mvnw", "spring-boot:run"]
