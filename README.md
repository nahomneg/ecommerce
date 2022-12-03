# Microservices Based Spring Boot

## Medium articles on the project
https://medium.com/@nahomnegdem/spring-boot-microservices-to-build-an-ecommerce-application-12e05f58e895


## How to run the project

- Make sure you have docker setup on your machine
- Make sure you have kubernetes setup on your machine


### Commands

You don't need to build the container as it is already on docker hub.
- All you need to do is run the following commands


>> kubectl apply -f ./  #everything will start run on your machine

>> http://localhost:9090/accounts/register create an account

>> http://localhost:9090/authenticate using username and password of the created account using Post method to generate JWT token

- for every request, make sure you use the generated token before except get all products