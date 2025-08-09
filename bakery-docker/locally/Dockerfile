FROM  amazoncorretto:21-alpine AS build
WORKDIR /app



ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","libs/bakery-api-1.0-SNAPSHOT.jar"]