FROM gradle:8.7 AS builder


RUN adduser worker
USER worker
WORKDIR /app
COPY --chown=worker:worker ./src ./
COPY --chown=worker:worker settings.gradle ./
COPY --chown=worker:worker gradlew ./
COPY --chown=worker:worker build.gradle ./
RUN gradle clean build

FROM tomcat:11.0.15-jre25-temurin-jammy
WORKDIR /app

RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /app/build/libs/ROOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
ENTRYPOINT [ "catalina.sh", "run"]