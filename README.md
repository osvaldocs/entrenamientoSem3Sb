HU3 – Catálogo de Eventos y Venues  
Arquitectura Hexagonal + JPA + Swagger + Paginación

Este proyecto implementa la Historia de Usuario HU3, refactorizando la aplicación hacia una arquitectura hexagonal (Ports & Adapters), manteniendo equivalencia funcional con HU2.  
Incluye persistencia con H2, Swagger, paginación y datos pre-cargados para testear.


------------------------------------------------------------
OBJETIVO
------------------------------------------------------------

Refactorizar el catálogo hacia arquitectura hexagonal, dividiendo el sistema en:

- Domain → Modelos del negocio.
- Application → Casos de uso (puertos de entrada).
- Infrastructure → Controladores, adaptadores JPA, configuraciones.

Se conserva el CRUD de HU2, pero ahora todo está desacoplado.


------------------------------------------------------------
PAGINACIÓN (Nueva funcionalidad HU3)
------------------------------------------------------------

El endpoint de listar eventos ahora soporta paginación.

Ejemplos:
GET /events?page=0&size=5
GET /events?page=1&size=10
GET /events?page=0&size=5&sort=date,desc

Swagger detecta automáticamente los parámetros.


------------------------------------------------------------
ESTRUCTURA DEL PROYECTO (HEXAGONAL)
------------------------------------------------------------

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
│   │   └── out
│   └── service
│
├── infrastructure
│   ├── controller
│   ├── adapter
│   ├── entity
│   ├── repository
│   └── config
│       ├── SwaggerConfig.java
│       └── DataSeeder.java
│
└── resources
    ├── application.properties
    └── data.sql (opcional)


------------------------------------------------------------
BASE DE DATOS
------------------------------------------------------------

Se usa H2 memoria.

spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

Consola H2:
http://localhost:8080/h2-console


------------------------------------------------------------
DATOS PRECARGADOS (DataSeeder)
------------------------------------------------------------

Incluye:
- 2 venues
- 2 eventos iniciales
- 50 eventos extra para probar paginación


------------------------------------------------------------
DEPENDENCIAS PRINCIPALES
------------------------------------------------------------

- Spring Web
- Spring Data JPA
- H2 Database
- Springdoc OpenAPI
- Lombok (opcional)

Swagger:
http://localhost:8080/swagger-ui/index.html


------------------------------------------------------------
ENDPOINTS
------------------------------------------------------------

EVENTS:
POST /events        → Crear
GET /events         → Listar con paginación
GET /events/{id}    → Buscar por ID
PUT /events/{id}    → Actualizar
DELETE /events/{id} → Eliminar

VENUES:
POST /venues        → Crear
GET /venues         → Listar
GET /venues/{id}    → Buscar por ID
PUT /venues/{id}    → Actualizar
DELETE /venues/{id} → Eliminar


------------------------------------------------------------
CRITERIOS DE ACEPTACIÓN HU3
------------------------------------------------------------

- Arquitectura Hexagonal implementada
- CRUD completo (Events y Venues)
- Servicios desacoplados por interfaces
- Adaptadores JPA funcionando
- Paginación funcional
- Swagger operativo
- H2 funcionando
- Código limpio y modular


------------------------------------------------------------
EJECUCIÓN
------------------------------------------------------------

mvn spring-boot:run

Swagger UI:
http://localhost:8080/swagger-ui/index.html

H2 Console:
http://localhost:8080/h2-console


------------------------------------------------------------
AUTOR
------------------------------------------------------------

Proyecto desarrollado como parte del entrenamiento Riwi — HU3: Arquitectura Hexagonal.
