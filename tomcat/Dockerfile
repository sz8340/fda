FROM openjdk:8-jre

RUN touch /tomcat-init-complete;
COPY docker-entrypoint.sh /entrypoint.sh
COPY healthcheck.sh /healthcheck.sh
ENTRYPOINT ["/entrypoint.sh"]
EXPOSE 8080
RUN mkdir /temp
VOLUME ["/temp1", "/temp2"]
COPY fda.war /usr/local/tomcat/webapps
HEALTHCHECK CMD /healthcheck.sh
CMD /usr/local/tomcat/bin/catalina.sh start && sleep infinity
