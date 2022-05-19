FROM openjdk:8-jdk-alpine
COPY target/springjwt-0.0.1.jar springjwt-0.0.1.jar
ENTRYPOINT ["java","-jar","/springjwt-0.0.1.jar"]