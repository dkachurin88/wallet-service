# Этап 1: Сборка приложения
FROM maven:3.8.7-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: Запуск приложения
FROM openjdk:17-jdk-slim
WORKDIR /app
# Копируем собранный JAR из первого этапа
COPY --from=builder /app/target/*.jar app.jar
# Эта команда запустится при старте контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]