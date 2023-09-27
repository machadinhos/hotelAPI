FROM openjdk:20-jdk

# Copy wait-for-it.sh script
COPY wait-for-it/wait-for-it.sh /wait-for-it.sh

# Run the wait-for-it.sh script to wait for the database to be ready
RUN chmod +x /wait-for-it.sh

# Set the working directory
WORKDIR /app

# Copy the Spring application JAR file into the container
COPY target/artifacts/hotelAPI_jar/hotelAPI.jar .

# Expose the port the Spring application listens on
EXPOSE 8080

# Command to run Spring Boot application
CMD ["java", "-jar", "hotelAPI.jar"]