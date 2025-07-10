FROM openjdk:24-jdk-slim AS builder

WORKDIR /app

# Copy only Maven wrapper and pom.xml first for caching
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Copy source code
COPY src ./src

# Build the application skipping tests
RUN ./mvnw clean package -DskipTests


FROM openjdk:24-jdk-slim AS prod

WORKDIR /app

# Copy the jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
