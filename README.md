# Getting Started

This application is used for user accreditation. 

# Stack : 

- Gradle - 7.4.1
- Spring Boot - 2.6.7


# Instructions to run the application: 

1) You can run this application by command line running the follow command on your terminal : 

**./gradlew build && java -jar build/libs/user-accreditation-0.0.1-SNAPSHOT.jar**

2) Also you can run a docker container, running the follow commands on your terminal: 

- **./gradlew build**
- **docker build --build-arg JAR_FILE=build/libs/user-accreditation-0.0.1-SNAPSHOT.jar --tag=user-accreditation:latest .**
- **docker run -p8080:8080 user-accreditation**

After that the application will be running on your machine on **port 8080**. You can check if the docker container is running using **docker ps -a**.

This application has an interface for users interact with it. If you access the follow URL
http://localhost:8080/swagger-ui/index.html#/user-accreditation-controller/saveAccreditation, this will open a Swagger that you can use to interact with. 

