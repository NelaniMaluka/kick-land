# Build phase
FROM maven:3.9-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run phase
FROM eclipse-temurin:17-alpine
COPY --from=build /target/Kick-Laand-0.0.1-SNAPSHOT.jar kickland.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kickland.jar"]
