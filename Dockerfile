FROM maven:3.8.4-openjdk-17 AS builder

# Définissez le répertoire de travail dans le conteneur
WORKDIR /backend
COPY . /backend/
RUN mvn clean package

#
FROM openjdk:17-alpine
# Définissez le répertoire de travail dans le conteneur
WORKDIR /backend
COPY --from=builder /backend/target/*.jar /backend/backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","backend.jar"]