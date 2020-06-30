# TaskTracker is a test task for Inmost company. 

This application allows to create and manage users and to assign and 
manage tasks for those users.

#### Technical details:

- Used Spring Boot and Maven for project setup;
- Spring Data JPA and MySQL database for persistent data;
- Spring security and JWT authentication;
- Data validation: standard javax constraints and custom validators;
- Exceptions handling;
- Lombok and ModelMapper to minimize boilerplate code;
- Swagger for API documentation;

#### To start and use the application:
- Download and open the application in your IDE. Open the 
application.properties file and update datasource.username and 
datasource.password to allow the application to create the database 
schema;  
- Launch the application. The database will be initialized
automatically with basic data(users, tasks, task statuses);
- API documentation is available at http://localhost:8080/swagger-ui.html
(check to update your localhost standard port if needed);
- In order to use public endpoints you need to get authenticated. 
So you need to send POST to localhost:8080/registration endpoint. 
Check the body of the request at Swagger docs. You'll receive the JSON
of your user DTO as a successful response, remember username and 
password you entered. Now go to http://localhost:8080/authentication
and enter in JSON your username and password. As a successful response
you'll receive JWT toke in a body of a response. Copy it to get the
possibility to use other endpoints. Now open Postman and set authorization
type to Bearer and paste the token to appropriate field. Now you may
use other API endpoints with help of Postman to send requests and Swagger -
to check the required JSON body;
