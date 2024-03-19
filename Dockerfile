FROM node AS compile_ts

WORKDIR /app
COPY . .
RUN npm install -g typescript && tsc

#コンパイル
FROM gradle:8.6.0-jdk17 AS compile_kt

WORKDIR /app
COPY --from=compile_ts /app .
RUN gradle wrapper && chmod +x ./gradlew && ./gradlew bootJar -i

#ダッシュボードを起動
FROM amazoncorretto:17 AS dashboard

WORKDIR /app

COPY --from=compile_kt /app/build/libs .

RUN ls
CMD ["java", "-jar", "SimpleTimer-Dashboard-0.0.1-SNAPSHOT.jar"]