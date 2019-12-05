FROM maven:3.5.4-jdk-8-alpine AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package


FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=build /usr/src/app/target/KafkaDemoApp.jar /usr/app/app.jar  
ENTRYPOINT ["java","-jar","-Dkafka.endpoints=${KAFKA_BOOTSTRAP_ENDPOINTS}", "/usr/app/app.jar"]  
EXPOSE 8885
