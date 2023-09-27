FROM openjdk:20-jdk

# Set the working directory
WORKDIR /app

# Copy the Spring application JAR file into the container
COPY target/artifacts/hotelAPI_jar/hotelAPI.jar /app/

# Expose the port the Spring application listens on
EXPOSE 8080

# Command to run Spring Boot application
CMD ["java", "-jar", "hotelAPI.jar"]