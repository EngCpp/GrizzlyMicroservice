# Use an official Java runtime as a base image
FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp

ADD grizzly-1.0.jar app.jar

RUN sh -c 'touch /app.jar'

# Make port 8080 available to the world outside this container
EXPOSE 8080 
EXPOSE 8787

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n", "-jar", "/app.jar"]

## CMD java -Djava.security.egd=file:/dev/./urandom -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n -jar /app.jar
