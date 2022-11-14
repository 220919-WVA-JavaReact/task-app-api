FROM amazoncorretto:11

COPY target/*.jar task-app.jar

ENTRYPOINT ["java", "-jar", "/task-app.jar"]