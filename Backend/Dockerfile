FROM maven:3.9-eclipse-temurin-17 AS build
COPY . .
RUN MVN clean package -SskipTests

FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
