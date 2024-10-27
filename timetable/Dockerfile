FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN ./gradlew build

EXPOSE 8083

CMD ["java", "-jar", "build/libs/timetable-0.0.1-SNAPSHOT.jar"]
