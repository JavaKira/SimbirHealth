FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN ./gradlew build

EXPOSE 8081

CMD ["java", "-jar", "build/libs/account-0.0.1-SNAPSHOT.jar"]
