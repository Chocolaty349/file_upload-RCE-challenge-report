ARG tomcat_version=10.1.24
FROM tomcat:${tomcat_version}-jre21

WORKDIR /usr/local/tomcat/webapps/

ADD upload_app.war .

USER root

EXPOSE 8080