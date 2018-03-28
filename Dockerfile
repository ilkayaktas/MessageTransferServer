FROM java:8
VOLUME /tmp
ADD out/artifacts/healthnetwork_main_jar/healthnetwork_main.jar healthnetwork_main.jar
EXPOSE 8080
RUN bash -c 'touch /healthnetwork_main.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/test", "-Djava.security.egd=file:/dev/./urandom","-jar","/healthnetwork_main.jar"]
