FROM gradle:6.7.0-jdk11-openj9 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

FROM openjdk:11-jre-slim
RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/user-manager.jar

ENTRYPOINT ["java", "-jar","/app/user-manager.jar"]

CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /user-manager.jar" ]