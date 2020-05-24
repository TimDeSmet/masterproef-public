FROM openjdk:8-alpine

EXPOSE 2000
WORKDIR /app

CMD ./mvnw package -DskipTests && java -jar ./target/CustomerService-0.0.1-SNAPSHOT.jar