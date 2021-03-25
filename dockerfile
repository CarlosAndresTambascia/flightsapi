FROM ubuntu:16.04

RUN apt-get update && apt-get install -y python-software-properties software-properties-common
RUN apt-get install -y iputils-ping
RUN apt-get install -y vim
RUN apt-get install -y postgresql-client

RUN apt-get update && apt-get install -y default-jdk maven
RUN add-apt-repository -y ppa:linuxuprising/java
RUN apt-get update
RUN dpkg --configure -a
RUN apt list | grep oracle-java
RUN echo "oracle-java16-1-installer shared/accepted-oracle-license-v1-2 boolean true" | debconf-set-selections
RUN apt install -y oracle-java15-installer
RUN java -version

RUN mkdir /opt/tomcat
RUN cd /opt/tomcat
RUN wget http://www.trieuvan.com/apache/tomcat/tomcat-7/v7.0.90/bin/apache-tomcat-7.0.90.tar.gz
RUN tar xvzf apache-tomcat-7.0.90.tar.gz
ADD . /usr/local/carlostambascia

# Run maven
RUN cd /usr/local/carlostambascia && mvn clean package

#CMD ["java","-jar","-DlogPath=/usr/local/carlostambascia", "/usr/local/carlostambascia/target/flightsapi-0.0.1-SNAPSHOT.war"]