# Build stage
FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /app

# First copy only the POM to cache dependencies
COPY pom.xml .
# Download dependencies (this layer will be cached unless POM changes)
RUN mvn dependency:go-offline

# Copy source files
COPY src ./src

# Build application (using the cached dependencies)
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built WAR file from the build stage
COPY --from=build /app/target/*.war app.war

# Optimized JVM settings for containerized environment
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/heap-dump.hprof"

EXPOSE 8081

# Use exec form for better signal handling
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.war"]