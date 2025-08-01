## Etapa de build com Gradle + JDK 17
#FROM gradle:8.4.0-jdk17 AS build
#
#WORKDIR /app
#
#COPY build.gradle settings.gradle gradlew /app/
#COPY gradle /app/gradle
#RUN gradle build -x test || return 0
#
#COPY src /app/src
#RUN gradle build -x test
#
## Etapa de produção usando JDK 17 (imagem leve)
#FROM openjdk:17-jdk-slim
#
#WORKDIR /app
#COPY --from=build /app/build/libs/*.jar app.jar
#
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
# # task-manager:1.0