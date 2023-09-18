# Tidsbanken Case 


The Tidsbanken app is a Java Spring Boot application designed to streamline the management of employee holiday requests and foster improved organizational communication. It simplifies and accelerates the process of handling employee holiday requests, saving valuable time and resources. The application seamlessly integrates with a PostgreSQL database, ensuring robust data storage and reliability. To ensure secure access and protect sensitive information, users undergo authentication and authorization via Keycloak, serving as the trusted identity provider.

## Installation

To set up the project, ensure you have the following prerequisites installed:

- Spring Boot: Install Spring Boot 2.7.preferred package manager.

- Maven: install Maven to manage project dependencies.

- Java: Ensure you have Java 17 installed on your system. 

# How To Use
## Login
To access the application, you must be authorized through Keycloak. There are two login options available:

Normal User:

- Username: client_user
- Password: user

Admin User:
- Username: client_admin
- Password: admin

Please select the appropriate login credentials based on your role within the application.

## Swagger
You can access comprehensive endpoint documentation through Swagger on our hosted Azure website by visiting the following link: [Swagger Documentation](https://tbanken.azurewebsites.net/swagger-ui/index.html#/) 

# Future works
- Add settings functionality
- Add vacationdays functionality

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
