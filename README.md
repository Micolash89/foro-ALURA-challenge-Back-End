# ForoHub

## Description

¡Bienvenido a nuestro último desafío Challenge Back End!

Un foro es un espacio donde todos los participantes de una plataforma pueden plantear sus preguntas sobre determinados tópicos. Aquí en Alura Latam, los estudiantes utilizan el foro para resolver sus dudas sobre los cursos y proyectos en los que participan. Este lugar mágico está lleno de mucho aprendizaje y colaboración entre estudiantes, profesores y moderadores.

Ya sabemos para qué sirve el foro y conocemos su aspecto, pero ¿sabemos cómo funciona detrás de escena? Es decir, ¿dónde se almacenan las informaciones? ¿Cómo se tratan los datos para relacionar un tópico con una respuesta, o cómo se relacionan los usuarios con las respuestas de un tópico?

Este es nuestro desafío, llamado ForoHub: replicar este proceso a nivel de back end creando una API REST usando Spring.

Nuestra API se centrará específicamente en los tópicos y debe permitir a los usuarios:

- Crear un nuevo tópico
- Mostrar todos los tópicos creados
- Mostrar un tópico específico
- Actualizar un tópico
- Eliminar un tópico

Esto es lo que normalmente conocemos como CRUD* (CREATE, READ, UPDATE, DELETE) y es muy similar a lo que desarrollamos en LiterAlura, pero ahora de forma completa, agregando las operaciones de UPDATE y DELETE, y usando un framework que facilitará mucho nuestro trabajo.

*Traducción libre (en orden): Crear, Consultar, Actualizar y Eliminar.

En resumen, nuestro objetivo con este challenge es implementar una API REST con las siguientes funcionalidades:

- API con rutas implementadas siguiendo las mejores prácticas del modelo REST
- Validaciones realizadas según las reglas de negocio
- Implementación de una base de datos relacional para la persistencia de la información
- Servicio de autenticación/autorización para restringir el acceso a la información

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (for development and testing)
- MySQL (for production)
- Spring Security
- Maven

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven 3.6+
- MySQL

### Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/tu-usuario/forohub.git
    cd forohub
    ```

2. Configure the database connection in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/forohub
    spring.datasource.username=tu-usuario
    spring.datasource.password=tu-contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build and run the application:

    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

## Usage

### Endpoints

- `POST /topics` - Create a new topic
- `GET /topics` - Retrieve all topics
- `GET /topics/{id}` - Retrieve a specific topic by ID
- `PUT /topics/{id}` - Update a topic by ID
- `DELETE /topics/{id}` - Delete a topic by ID

### Authentication

For the authentication and authorization, we are using Spring Security. You will need to register and log in to obtain a token to access the protected endpoints.

## Contributing

If you want to contribute to this project, please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
