FROM openjdk:8u171-jre

COPY jjdz5-ants-1.0-SNAPSHOT-jar-with-dependencies.jar /target/app.jar
COPY dataBaseDefaultTR.json /dataBaseDefaultTR.json
COPY dataBasePlaces.json /dataBasePlaces.json

CMD java -jar /target/app.jar