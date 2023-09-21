# Stage 1: Build and Test the application
FROM maven:3.9.3-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
# Use go-offline to force Maven to resolve all dependencies
RUN mvn dependency:go-offline
# Copy the source code into the container
COPY src ./src
# Debugging purpose, also cleaning the cache (Optional)
RUN rm -rf /root/.m2/repository
# Build the application
RUN mvn package

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-alpine
WORKDIR /app
# Copy the compiled JAR file from the build stage into the runtime image
COPY --from=build /app/target/Tidsbanken-0.0.1-SNAPSHOT.jar /app/demo.jar
# Expose the port the application listens on
EXPOSE 8081
# Run the application
CMD ["java", "-jar", "demo.jar"]
