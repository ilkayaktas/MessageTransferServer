FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD healthservice-0.1.0.jar
ENTRYPOINT [“java”,”-Djava.security.egd=file:/dev/./urandom”,”-jar”,”/healthservice-0.0.1.jar”]