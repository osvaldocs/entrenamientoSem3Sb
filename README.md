HU3 – Catálogo de Eventos y Venues

Arquitectura Hexagonal + Spring Boot 3 + Spring Data JPA + Swagger + Paginación

Este proyecto implementa la Historia de Usuario HU3, donde se refactoriza la aplicación hacia una arquitectura hexagonal (Ports & Adapters) manteniendo equivalencia funcional con HU2.
Incluye persistencia con Spring Data JPA (H2), validaciones, documentación Swagger, datos iniciales de prueba y paginación completa.

🚀 Objetivo de la HU3

Adoptar una arquitectura hexagonal que desacople completamente:

Domain → Modelos del negocio.

Application → Casos de uso.

Infrastructure → Controladores REST, adaptadores JPA, configuración.

La aplicación conserva el CRUD de HU2, pero ahora con persistencia real, puertos/servicios desacoplados y paginación en los listados.

🔄 Paginación (Nueva funcionalidad HU3)

El endpoint de listar Eventos soporta paginación usando Pageable de Spring Data.

✔ Endpoint

GET /events?page={num}&size={num}&sort={campo,asc|desc}


✔ Ejemplos

Primera página con 5 eventos:

GET /events?page=0&size=5


Ordenados por fecha descendente:

GET /events?page=0&size=5&sort=date,desc

✔ Ejemplo de respuesta real

{
  "content": [
    { "id": 1, "name": "Rock Festival" },
    { "id": 2, "name": "Charity Gala" }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5
  },
  "totalPages": 11,
  "totalElements": 52,
  "last": false,
  "first": true
}

Swagger detecta automáticamente page, size y sort, permitiendo probar la paginación desde la UI.


📂 Estructura del Proyecto (Arquitectura Hexagonal)

src/main/java/com.riwi.H3
│
├── domain
│   └── model
│       ├── Event.java
│       └── Venue.java
│
├── application
│   ├── port
│   │   ├── in
│   │   │   ├── EventUseCase.java
│   │   │   └── VenueUseCase.java
│   │   └── out
│   │       ├── EventRepositoryPort.java
│   │       └── VenueRepositoryPort.java
│   └── service
│       ├── EventServiceImpl.java
│       └── VenueServiceImpl.java
│
├── infrastructure
│   ├── controller
│   │   ├── EventController.java
│   │   └── VenueController.java
│   ├── adapter
│   │   ├── EventJpaAdapter.java
│   │   └── VenueJpaAdapter.java
│   ├── entity
│   │   ├── EventEntity.java
│   │   └── VenueEntity.java
│   ├── repository
│   │   ├── EventJpaRepository.java
│   │   └── VenueJpaRepository.java
│   └── config
│       ├── SwaggerConfig.java
│       └── DataSeeder.java
│
└── resources
    ├── application.properties
    └── data.sql (opcional)

🗄️ Base de Datos – H2

Configuración típica:

spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

Consola H2:

http://localhost:8080/h2-console


🌱 Datos de prueba (Seed Data)

Se cargan automáticamente usando CommandLineRunner:

2 Venues

2 Eventos iniciales

+50 eventos generados para probar paginación

Esto permite desarrollar y testear sin necesidad de cargar nada manualmente.


📘 Documentación Swagger / OpenAPI

Disponible en:

http://localhost:8080/swagger-ui/index.html

Controladores documentados con:

@Tag

@Operation

@ApiResponse

📝 Endpoints Principales
Events
Método	Ruta	Descripción
POST	/events	Crear evento
GET	/events	Listar (con paginación)
GET	/events/{id}	Obtener por ID
PUT	/events/{id}	Actualizar
DELETE	/events/{id}	Eliminar

mvn spring-boot:run




    Swagger: http://localhost:8080/swagger-ui/index.html

H2 Console: http://localhost:8080/h2-console
👨‍💻 Autor

Proyecto desarrollado como parte del entrenamiento Riwi — HU3: Arquitectura Hexagonal.

