# Use official base image of Java Runtime
FROM openjdk:8

# Make port 8080 available to the world outside container
EXPOSE 8080
LABEL maintainer="pattanacode.com"

ADD target/pattanacode-api-services.jar pattanacode-api-services.jar
ENTRYPOINT ["java","-jar","pattanacode-api-services.jar","com.pattanacode.PattanaCodeMainApplication"]

