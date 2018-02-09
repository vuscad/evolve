FROM frolvlad/alpine-oraclejdk8:slim

ADD ./build/libs/evolve-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5005

EXPOSE 8085

CMD java -jar ${ADDITIONAL_OPTS} app.jar