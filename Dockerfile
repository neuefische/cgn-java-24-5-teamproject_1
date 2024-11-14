# change setting here
FROM openjdk:23

EXPOSE 8080

COPY backend/target/GroceryShopping-App.jar GroceryShopping-App.jar

ENTRYPOINT ["java", "-jar", "GroceryShopping-App.jar"]