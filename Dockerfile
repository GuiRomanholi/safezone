FROM gradle:jdk17-alpine AS builder
 
WORKDIR /app
 
COPY . .
 
RUN adduser -h /home/gui -s /bin/bash -D gui
 
RUN chown -R gui:gui /app
 
USER gui
 
RUN ./gradlew build --no-daemon

FROM openjdk:17-jdk-alpine

ENV JAR_FILE=safezone-1.0.0.jar
 
WORKDIR /app

COPY --from=builder /app/build/libs/${JAR_FILE} /app/app.jar
 
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
