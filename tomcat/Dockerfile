FROM openjdk:8-jre

COPY docker-entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

COPY healthcheck.sh /healthcheck.sh
EXPOSE 8080
COPY fda.war /usr/local/tomcat/webapps
HEALTHCHECK CMD /healthcheck.sh
CMD /usr/local/tomcat/bin/catalina.sh start && sleep infinity
