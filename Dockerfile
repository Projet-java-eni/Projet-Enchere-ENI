FROM tomcat:9.0-jdk8-corretto

COPY . webapps/

CMD ["java", "-cp", "src/main/webapp/WEB-INF/lib/mssql-jdbc-9.4.0.jre8.jar:src/main/webapp/WEB-INF/lib/jstl-1.2.jar", "bin/catalina.sh", "run"]
