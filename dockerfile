FROM tomcat:9.0.45-jdk15-adoptopenjdk-openj9
COPY /target/flightsapi-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/