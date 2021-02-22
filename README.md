# Restaurant
restaurant service on Spring Boot

### Description:

There are roles: Customer, Manager.

The customer (authorized user) makes orders from the menu (the catalog of dishes).
He can filter the list of dishes by categories
and has the opportunity look through the catalog with sorting:
- by the name of the dish;
- by price;
- by category.

The customer, within one order, can order several identical dishes.

The manager manages orders: after receiving a new order, sends it for cooking.
After cooking, the manager transfers  order for delivery.
After delivery and receipt of payment, the manager transfers the status of the order to "done".

### Implementation:
- Front-End - HTML, CSS, JavaScript, AngularJS
- Back-end - Java 8, SQL

### Technologies:
- Spring Boot
- Spring Security
- PostgreSQL
- Maven

### Usage:

1. ```git clone https://github.com/trohalska/restaurant```
2. apply maven wrapper: ```mvn -N io.takari:maven:wrapper```
3. postgreSQL database:

    3.1. Install PostgreSQL.

    3.2. Create user and databases:
   
   ```
   psql postgres; 
   CREATE USER root WITH PASSWORD [your password];
   CREATE DATABASE [your login];
   CREATE DATABASE restaurant;
   exit;
   ```
    
    3.3. Write postgresql password (for root) in ```application.properties``` (src/main/resources/application.properties) as password field.

    3.4. You can insert basic data to database tables from ```data.sql``` file. Basic manager: login ```manager```, password ```password``` will be created by application.

4. run server: ```./mvnw spring-boot:run```
5. open website: ```http://localhost:8080```


### View:
![view game](https://raw.githubusercontent.com/trohalska/restaurant/main/src/main/resources/pic/pic01.png)
![view game](https://raw.githubusercontent.com/trohalska/restaurant/main/src/main/resources/pic/pic02.png)
![view game](https://raw.githubusercontent.com/trohalska/restaurant/main/src/main/resources/pic/pic03.png)
![view game](https://raw.githubusercontent.com/trohalska/restaurant/main/src/main/resources/pic/pic04.png)
![view game](https://raw.githubusercontent.com/trohalska/restaurant/main/src/main/resources/pic/pic05.png)
![view game](https://raw.githubusercontent.com/trohalska/restaurant/main/src/main/resources/pic/pic06.png)
![view game](https://raw.githubusercontent.com/trohalska/restaurant/main/src/main/resources/pic/pic07.png)
![view game](https://raw.githubusercontent.com/trohalska/restaurant/main/src/main/resources/pic/pic08.png)
