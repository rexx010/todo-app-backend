# Use a lightweight JDK image
FROM openjdk:24-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR file from Maven build output
COPY target/*.jar app.jar

# Expose your Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
