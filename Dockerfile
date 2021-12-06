FROM maven:3.6.0-jdk-11-slim AS build

COPY ./ ./

RUN mvn clean install -DskipTests

CMD ["java", "-jar", "target/product-ms-0.0.1-SNAPSHOT.jar"]