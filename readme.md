This project is responsible for managing ATM machine operations like checking and withdrawing balance.

This is a Spring boot application which exposes below operations.

http://<server name > : <server port > /fetchBalance/{accountNo}/{pin}  --> Checking balance avaliable in account

http://<server name > : <server port > /dispenseMoney --> To dispense requested money

Database :

Application use in memory H2 database. Properties releated to dev database are configured in application-dev.properties file.

Database url : jdbc:h2:mem:devdb
username : sa
Password is not required.

H2 console url : http://localhost:8080/h2-console

How to run the application on local machine :

1) Right click and go to run as configuration and mentioned below property in VM argument to run in dev profile.

	-Dspring.profiles.active=dev

2) Click run and it run the application. We can inovoke required api using url as mentioned in above sections.


How to run in any enviroment :

1)Run mvn clean install and generate jar
2)java -jar <jar name> -Dspring.profiles.active=dev 