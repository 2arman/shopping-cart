FROM maven:3.6.3-jdk-11 AS build-env

WORKDIR /app
COPY . .

ARG MAVEN_OPTS

RUN mvn clean package

FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine

ENV TZ=Asia/Tehran

RUN sed -i "s|dl-cdn|dl-4|" /etc/apk/repositories

RUN apk add --update --no-cache tzdata

RUN mkdir -p /root/.m2 && chmod -R g+rws,a+rx /root/.m2

WORKDIR /usr/local/app/

COPY --from=build-env /app/target/*.jar /usr/local/app/

RUN mv /usr/local/app/shopping-cart*.jar /usr/local/app/shopping-cart.jar

EXPOSE 8080

CMD ["sh", "-c", "java -jar /usr/local/app/shopping-cart.jar"]
