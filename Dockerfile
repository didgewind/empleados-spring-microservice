FROM openjdk:8-jdk-alpine
COPY target/empleados-rest-0.0.1-SNAPSHOT.jar empleados-rest-0.0.1-SNAPSHOT.jar
EXPOSE 5555
ENTRYPOINT ["java", "-DEUREKA_SERVER=http://eureka-service:1111/eureka","-jar","/empleados-rest-0.0.1-SNAPSHOT.jar"]