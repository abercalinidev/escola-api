# ===== 1) Build Stage: Maven 3.9.8 =====
FROM maven:3.9.8-amazoncorretto-21 AS builder

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando apenas o pom.xml primeiro para aproveitar cache
COPY pom.xml .

# Baixar dependências antes (melhora performance)
RUN mvn dependency:go-offline -B

# Copia todo o restante do projeto
COPY . .

# Compila o projeto e gera o jar
RUN mvn clean package -DskipTests

# ===== 2) Run Stage: Somente JDK =====
FROM amazoncorretto:21-alpine

WORKDIR /app

# Copia o JAR gerado no estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
