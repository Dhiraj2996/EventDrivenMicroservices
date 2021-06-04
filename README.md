# EventDrivenMicroservices
Event-driven microservices: Spring boot, kafka and elastic (Still in developement)

There are two services:
  Twitter to Kafka service: read twitter tweets and writes to kafka
  Kafka to elastic service
  
Config server
  -refering to git repo : https://github.com/Dhiraj2996/Microservices-config-server-repository
  -is password protected. you can add your jce encrypted key in both services and config server. 
  - set JASYPT_ENCRYPTOR_PASSWORD environment variable with encryption key

How to run:
  - make sure docker is running
  - mvn clean install 
  - go to docker-compose: docker-compose up

Further TODO:
  Query service(with web UI): To query elastic search documents, 
  Securing services,
  Service Discovery with Eureka,
  API Gateway
  
  
