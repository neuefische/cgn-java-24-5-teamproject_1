# change setting here
FROM temurin:23

COPY backend/target/may-app.jar my-app.jar

ENTRYPOINT ["java", "-jar", "my-app.jar"]