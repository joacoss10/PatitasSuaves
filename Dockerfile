# 1. Etapa de compilación con Maven y JDK 21 (compatible con proyectos Java 17 y 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Buscamos el archivo .jar ejecutable (ignorando el -plain.jar) y lo renombramos a app.jar
RUN find target/ -name "*.jar" ! -name "*-plain.jar" -exec mv {} target/app.jar \;

# 2. Etapa de ejecución liviana con JRE 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
