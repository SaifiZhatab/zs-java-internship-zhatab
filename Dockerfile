FROM openjdk:11
ADD build/libs/SpringBootGradleProject-1.0-SNAPSHOT.jar  hobbies.jar

EXPOSE 2020

ENTRYPOINT ["java" ,"-jar" , "hobbies.jar"]