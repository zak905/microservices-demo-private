FROM tomcat:8-jre8-alpine

COPY target/userreviewservice-0.1.war $CATALINA_HOME/webapps/userreview.war

RUN sed -i -e 's/port="8080"/port="9393"/g' $CATALINA_HOME/conf/server.xml

EXPOSE 9393

CMD ["catalina.sh", "run"]

