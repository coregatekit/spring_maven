FROM openjdk:jre-alpine
WORKDIR /opt
COPY ./target/Spring-Maven-0.0.1-SNAPSHOT.jar /opt/demo.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/demo.jar"]