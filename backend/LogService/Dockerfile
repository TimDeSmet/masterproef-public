FROM openjdk:8-alpine

EXPOSE 2002
WORKDIR /app

CMD ./mvnw package -DskipTests && java -jar ./target/LogService-0.0.1-SNAPSHOT.jar