FROM tomcat:8-jre8-alpine

COPY target/userreviewservice-0.1.war $CATALINA_HOME/webapps/userreview.war

#EXPOSE 8080
CMD ["catalina.sh", "run"]

