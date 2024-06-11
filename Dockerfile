#FROM maven:3.8.4-openjdk-17 AS builder
#
## Définissez le répertoire de travail dans le conteneur
#WORKDIR /app
#COPY . /app/
#RUN mvn clean package
#
##
#FROM openjdk:17-alpine
## Définissez le répertoire de travail dans le conteneur
#WORKDIR /app
#COPY --from=builder /app/target/*.jar /app/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/backend-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","backend.jar"]
