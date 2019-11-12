FROM openjdk:jre-alpine
COPY ./target/demo-0.0.1-SNAPSHOT.jar /opt/demo.jar
WORKDIR /opt
EXPOSE 8080
CMD ["java", "-jar", "/opt/demo.jar"]