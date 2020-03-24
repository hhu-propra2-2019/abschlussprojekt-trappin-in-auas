FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM openjdk:11-jre-slim
COPY . /usr/src
WORKDIR /usr/src
RUN javac Bewerbung1Application.java
CMD ["java", "Bewerbung1Application"]




