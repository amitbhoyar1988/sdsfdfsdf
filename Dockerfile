# Alpine Linux with OpenJDK JRE
FROM openjdk:13-alpine

# Copy war file
COPY /target/deliziahr-businesscycle-service-0.0.1-SNAPSHOT.jar /echo.jar
COPY /keystore.p12 /keystore.p12
  
# run the app
CMD ["/opt/openjdk-13/bin/java", "-jar", "/echo.jar"]