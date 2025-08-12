FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /home/app

#Downloading and compiling all dependency
COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/mir/survey/SurveyApplication.java /home/app/src/main/java/com/mir/survey/SurveyApplication.java

RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Compiling our application
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Docker magic
FROM openjdk:21-jdk
EXPOSE 8080
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c" ,"java -jar app.jar"]