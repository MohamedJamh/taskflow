FROM openjdk:17
ADD target/auth-service.jar auth-service
ENTRYPOINT ["java", "-jar","auth-service"]