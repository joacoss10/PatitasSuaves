# 1. Etapa de compilación con Maven y Java 17
FROM maven:3.8.8-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# 2. Etapa de ejecución de nuestro Spring Boot
FROM eclipse-temurin:17-jdk-alpine
# Buscamos cualquier jar que se haya generado y lo renombramos a app.jar
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
# Ejecutamos directamente el archivo renombrado sin usar asteriscos
ENTRYPOINT ["java","-jar","app.jar"]