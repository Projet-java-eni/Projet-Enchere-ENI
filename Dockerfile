FROM tomcat:9.0-jdk8-corretto

COPY . webapps/

CMD ["bin/catalina.sh", "run"]
