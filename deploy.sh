ant clean war
rm -f /opt/apache-tomcat-8.5.24/webapps/fda.war
rm -rf /opt/apache-tomcat-8.5.24/webapps/fda
cp dist/fda.war /opt/apache-tomcat-8.5.24/webapps
/opt/apache-tomcat-8.5.24/bin/catalina.sh stop
/opt/apache-tomcat-8.5.24/bin/catalina.sh start
