FROM openjdk:8-jdk-alpine
MAINTAINER vpnkmr764@gmail.com
COPY target/ATM-0.0.1-SNAPSHOT.jar ATM-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ATM-0.0.1-SNAPSHOT.jar"]