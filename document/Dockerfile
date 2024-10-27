FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN ./gradlew build

EXPOSE 8084

CMD ["java", "-jar", "build/libs/document-0.0.1-SNAPSHOT.jar"]
