# Tidsbanken Case 

The Tidsbanken app is a Java Spring Boot application designed to streamline the management of employee holiday requests and foster improved organizational communication. It simplifies and accelerates the process of handling employee holiday requests, saving valuable time and resources. The application seamlessly integrates with a PostgreSQL database, ensuring robust data storage and reliability. To ensure secure access and protect sensitive information, users undergo authentication and authorization via Keycloak, serving as the trusted identity provider.

[Github Repo - Frontend](https://github.com/williamevans98/TidsbankenFE)

# Installation

To set up the project, ensure you have the following prerequisites installed:

- Spring Boot: Install Spring Boot 2.7.preferred package manager.

- Maven: install Maven to manage project dependencies.

- Java: Ensure you have Java 17 installed on your system.

## Quick Start
### 1. Clone the Repository
```bash
git clone https://github.com/your-repository.git
```
### 2.  Navigate to the Project Folder
```bash
cd your-project-folder
```
### 3. Start PostgreSQL database
```bash
sudo service postgresql start
```
### 4. Create a Database
Open PgAdmin/DBeaver and create a new database. Alternatively, you can create a new database via the command line:
```bash
createdb your_database_name
```
### 5. Update `application.properties`
Navigate to `src/main/resources/` and open `application.properties`. Update the database URL, username, and password.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```
### 6. Run and documentation
Open the project in IntelliJ and click on the `Run` button

# Technical documentation

## Swagger
You can access comprehensive endpoint documentation through Swagger on our hosted Azure website by visiting the following link: 

[Swagger Documentation](https://tbanken.azurewebsites.net/swagger-ui/index.html#/) 

## API Responses
The RESTful APIs adhere to the following response guidelines:
- Most successful requests return either a 200 OK, 201 Created, or 204 No Content response.
- All instances where a database record is created return a 201 Created response, along with the location of the created resource (ID).
- Instances where input validation fails or incorrect data is passed to an endpoint return a 400 Bad Request response.
- Unauthenticated user attempts to access strictly authorized endpoints return a 401 Unauthorized response, even if the requested resources don't exist.
- Requests where the user is authenticated but not authorized to view a particular resource return a 403 Forbidden response.
- Requests made to unknown endpoints return a 404 Not Found response.
- Any request that causes the server to enter an error state fails immediately, without exiting the server process, and returns a 500 Internal Server Error response.

## Database
![ER-diagram](https://github.com/victorlun/TidsbankenBE/assets/138670212/955d645a-12ac-47e8-a192-e73b0c3f8aa6)


We use a PostgreSQL database that is hosted on Azure.

Relationship between entites are as follow:
 - employee.employee_id are FK to blocked_period.employee_id 
 - employee.employee_id are FK to employee.manager_id
 - employee.employee_id are FK to vacation_request.employee_id
 - vacation_request.vacation_request_id are FK to vacation_response.vacation_request_id


# Built With
[IntelliJ IDEA](https://www.jetbrains.com/idea/)

[Spring Framework](https://spring.io/)

[Hibernate](https://hibernate.org/)

[PostgreSQL](https://www.postgresql.org/)

[Swagger](https://swagger.io/)

[Docker](https://www.docker.com/)

[Azure](https://portal.azure.com)

Collaborators

[Justine Jensen](https://github.com/JustineJensen)

[Victor Lundqvist](https://github.com/victorlun)

[Nathalie Levenfalk](https://github.com/levenfalk)

[William Evans](https://github.com/williamevans98)


## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
