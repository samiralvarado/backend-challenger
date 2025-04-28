# Usa la imagen base oficial de OpenJDK 21
FROM openjdk:21

# Define el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación (suponiendo que lo hayas construido previamente con Maven o Gradle)
COPY target/challenge-backend-0.0.1-SNAPSHOT.jar /app/challenge-backend-0.0.1-SNAPSHOT.jar

# Expon el puerto en el que la aplicación escuchará (usualmente 8080 en Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "challenge-backend-0.0.1-SNAPSHOT.jar"]
