FROM java:8-jdk-alpine
COPY target/malzeme-0.0.1-SNAPSHOT.jar /app.jar
CMD  ["java","-jar","app.jar"]