Spring Boot Application




Spring Boot - Framework to build the application
Spring Data JPA - For database interaction
Spring Security - For authentication and authorization
mySQL Database.
Maven - For project management and build
Getting Started

Prerequisites
Java 17 or higher
Maven 3.6+
Installation
Clone the repository:
bash
Copy code
git clone 
cd your-repository
Build the project:
bash
Copy code
mvn clean install
Running the Application
Run the application using Maven:
bash
Copy code
mvn spring-boot:run
Alternatively, you can run the JAR file directly:

bash
Copy code
java -jar target/your-application-name.jar
Access the application:
The application should be running on http://localhost:8000.
Configuration

The application can be configured using the application.properties or application.yml file located in the src/main/resources directory.

Example properties:

properties
 the file included is included



Sample SQL for Initial Data:
 seed data included in the code 