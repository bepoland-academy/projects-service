FROM openjdk:8

MAINTAINER Lukasz Franczuk <l.franczuk@be-tse.com>

WORKDIR /application
COPY target/projects-service.jar /application/app.jar

RUN echo "Europe/Stockholm" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata
CMD ["/bin/sh", "-c", "java -jar /application/app.jar"]

