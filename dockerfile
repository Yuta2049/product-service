FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
EXPOSE 8080
ARG JAR_FILE=/build/libs/product-service-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]