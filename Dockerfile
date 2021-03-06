FROM openjdk:8

MAINTAINER Lukasz Franczuk <l.franczuk@be-tse.com>

WORKDIR /application
COPY target/projects-service.jar /application/app.jar

CMD ["/bin/sh", "-c", "java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=4444 -jar /application/app.jar"]

