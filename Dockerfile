FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN mvn -q -B dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENTRYPOINT ["sh","-c","java -Xms256m -Xmx512m -jar /app/app.jar"]
