FROM openjdk:8-alpine
EXPOSE 8080
WORKDIR /app
CMD ./mvnw package -DskipTests && java -jar ./target/APIGateway-0.0.1-SNAPSHOT.jar