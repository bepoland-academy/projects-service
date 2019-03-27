FROM openjdk:8

MAINTAINER Lukasz Franczuk <l.franczuk@be-tse.com>

WORKDIR /application
COPY target/projects-service.jar /application/app.jar

RUN sudo echo "Europe/Stockholm" > /etc/timezone
RUN sudo dpkg-reconfigure -f noninteractive tzdata
CMD ["/bin/sh", "-c", "java -jar /application/app.jar"]

