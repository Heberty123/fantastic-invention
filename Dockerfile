FROM gradle:8.1.0-jdk17 as builder
WORKDIR /builded/
COPY build.gradle /builded/build.gradle
COPY gradle /builded/gradle
COPY gradlew /builded/gradlew
COPY src /builded/src
RUN chmod +x /builded/gradlew
RUN /builded/gradlew build -x test

FROM openjdk:17-jdk 
WORKDIR /app/
COPY --from=builder /builded/build/libs/*.jar /app/app.jar
COPY . /app/
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]