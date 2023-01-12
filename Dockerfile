FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 8081
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/api-0.0.1-SNAPSHOT.jar /app/kotlin-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=test", "-jar", "/app/kotlin-app.jar"]