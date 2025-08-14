# syntax=docker/dockerfile:1.7

######## Build ########
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

COPY --chmod=0755 gradlew ./
RUN sed -i 's/\r$//' gradlew || true
COPY gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.jar
COPY gradle/wrapper/gradle-wrapper.properties gradle/wrapper/gradle-wrapper.properties

# Kotlin DSL files & (optional) gradle.properties
COPY *.gradle.kts ./
# COPY gradle.properties ./

# Warm cache
RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon help

# App sources
COPY src/ ./src/

# Build jar and rename it deterministically to app.jar
RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon clean bootJar -x test \
 && ls -l build/libs \
 && JAR="$(ls build/libs/*.jar | head -n 1)" \
 && echo "Using JAR: $JAR" \
 && mv "$JAR" build/libs/app.jar

######## Runtime ########
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app
RUN useradd --system --create-home --home-dir /app spring

# Copy the known filename
COPY --from=build /workspace/build/libs/app.jar /app/app.jar
RUN chown -R spring:spring /app
USER spring

ENV SPRING_PROFILES_ACTIVE=prod \
    SERVER_PORT=8080 \
    JAVA_OPTS="-XX:InitialRAMPercentage=25 -XX:MaxRAMPercentage=60 -XX:+UseG1GC -XX:+UseStringDeduplication -XX:+ExitOnOutOfMemoryError -Djava.security.egd=file:/dev/urandom"

EXPOSE 8080
ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /app/app.jar --server.port=${SERVER_PORT}"]
