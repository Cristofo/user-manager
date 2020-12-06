

FROM openjdk:11-jre-slim
RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/user-manager.jar