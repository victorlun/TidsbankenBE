# Tidsbanken Case 

The Tidsbanken app is a Java Spring Boot application designed to streamline the management of employee holiday requests and foster improved organizational communication. It simplifies and accelerates the process of handling employee holiday requests, saving valuable time and resources. The application seamlessly integrates with a PostgreSQL database, ensuring robust data storage and reliability. To ensure secure access and protect sensitive information, users undergo authentication and authorization via Keycloak, serving as the trusted identity provider.

## Installation

To set up the project, ensure you have the following prerequisites installed:

- Spring Boot: Install Spring Boot 2.7.preferred package manager.

- Maven: install Maven to manage project dependencies.

- Java: Ensure you have Java 17 installed on your system. 

## Swagger
You can access comprehensive endpoint documentation through Swagger on our hosted Azure website by visiting the following link: [Swagger Documentation](https://tbanken.azurewebsites.net/swagger-ui/index.html#/) 

# API Responses
The RESTful APIs adhere to the following response guidelines:
- Most successful requests return either a 200 OK, 201 Created, or 204 No Content response.
- All instances where a database record is created return a 201 Created response, along with the location of the created resource (ID).
- Instances where input validation fails or incorrect data is passed to an endpoint return a 400 Bad Request response.
- Unauthenticated user attempts to access strictly authorized endpoints return a 401 Unauthorized response, even if the requested resources don't exist.
- Requests where the user is authenticated but not authorized to view a particular resource return a 403 Forbidden response.
- Requests made to unknown endpoints return a 404 Not Found response.
- Any request that causes the server to enter an error state fails immediately, without exiting the server process, and returns a 500 Internal Server Error response.

# Built With
[IntelliJ IDEA] (https://www.jetbrains.com/idea/)

[Spring Framework](https://spring.io/)

[Hibernate](https://hibernate.org/)

[PostgreSQL](https://www.postgresql.org/)

[Swagger](https://swagger.io/)

[Docker](https://www.docker.com/)

[Azure](https://portal.azure.com)

Developed By

[Justine Jensen](
JustineJensen)

[Victor Lundqvist](victorlun)


## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
