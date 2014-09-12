FROM dockerfile/java:oracle-java8

MAINTAINER Bálder Carraté <balder.carrate@otium360.com>

ADD ${project.build.finalName}.jar /data/poc.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/data/poc.jar"]
