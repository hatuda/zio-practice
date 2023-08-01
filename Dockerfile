# first stage

FROM sbtscala/scala-sbt:eclipse-temurin-17.0.3_1.7.1_2.13.8 AS build

COPY ./ ./app

WORKDIR app

RUN sbt assembly

# second stage

FROM eclipse-temurin:17.0.4_8-jre

COPY --from=build /root/app/target/scala-3.3.0/*.jar /zio-hands-on.jar