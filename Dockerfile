# Usando Java 21
FROM amazoncorretto:21

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiando o JAR da aplicação para o container
COPY ./target/escola-api-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
