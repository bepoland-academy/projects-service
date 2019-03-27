FROM openjdk:8

MAINTAINER Lukasz Franczuk <l.franczuk@be-tse.com>

WORKDIR /application
COPY target/projects-service.jar /application/app.jar

RUN timedatectl set-timezone CET
CMD ["/bin/sh", "-c", "java -jar /application/app.jar"]

