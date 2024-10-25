# Используем официальный образ OpenJDK
FROM openjdk:11-jre-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл pom.xml и зависимости
COPY pom.xml .

# Копируем исходный код
COPY src ./src

# Собираем проект
RUN ./mvnw package -DskipTests

# Копируем собранный jar-файл
COPY target/time-tracker-0.0.1-SNAPSHOT.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
