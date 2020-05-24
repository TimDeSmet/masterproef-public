FROM openjdk:8-alpine

EXPOSE 2001
WORKDIR /app

CMD ./mvnw package -DskipTests && java -jar ./target/ProductService-0.0.1-SNAPSHOT.jar