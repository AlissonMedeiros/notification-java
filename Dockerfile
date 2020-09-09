FROM openjdk:11-jre-slim

ENV TZ America/Sao_Paulo

COPY ./application/target/application.jar /app/

ENTRYPOINT exec java $JAVA_OPTIONS -jar /app/application.jar

EXPOSE 8080
