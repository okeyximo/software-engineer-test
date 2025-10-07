FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . .
RUN ./gradlew bootJar
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/student-score-api-1.0.0.jar"]
