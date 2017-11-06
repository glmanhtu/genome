FROM java:openjdk-8-jdk-alpine

# add directly the jar
ADD target/*.jar /app.jar

# creates a mount point
VOLUME /tmp

COPY wait-for-command.sh /usr/local/wait-for-command.sh

RUN ["chmod", "+x", "/usr/local/wait-for-command.sh"]

ENTRYPOINT /usr/local/wait-for-command.sh -c 'nc -z genome-db 3306' -t 30 &&  /usr/bin/java -jar /app.jar --spring.profiles.active=docker

EXPOSE 8080