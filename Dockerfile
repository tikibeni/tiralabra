FROM openjdk:11

WORKDIR /app

COPY . /app/

RUN chmod +x ./gradlew

RUN ./gradlew install

CMD ["./gradlew", "run"]