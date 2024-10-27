FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN ./gradlew build

EXPOSE 8082

CMD ["java", "-jar", "build/libs/hospital-0.0.1-SNAPSHOT.jar"]
