# 🚀 HU3 -- Refactor a Arquitectura Hexagonal (Ports & Adapters)

Este proyecto implementa **arquitectura hexagonal**, migrando desde un
CRUD tradicional hacia una estructura más limpia, mantenible y
escalable.\
Incluye **mapeos con MapStruct**, **puertos de entrada/salida**,
**adaptadores**, **servicios**, y **Swagger/OpenAPI**, junto con
paginación en los endpoints de listado.

------------------------------------------------------------------------

## 📂 Estructura del Proyecto

    src/main/java/com/riwi/H3
    │
    ├── domain
    │   ├── model
    │   │   └── Event.java
    │   │   └── Venue.java
    │   ├── port
    │   │   ├── in
    │   │   │   └── EventUseCase.java
    │   │   │   └── VenueUseCase.java
    │   │   └── out
    │   │       └── EventRepositoryPort.java
    │   │       └── VenueRepositoryPort.java
    │   └── service
    │       └── EventServiceImpl.java
    │       └── VenueServiceImpl.java
    │
    ├── infrastructure
    │   ├── adapter
    │   │   └── repository
    │   │       └── JpaEventRepository.java
    │   │       └── JpaVenueRepository.java
    │   ├── controller
    │   │   └── EventController.java
    │   │   └── VenueController.java
    │   └── mapper
    │       └── EventMapper.java
    │       └── VenueMapper.java
    │
    └── configuration
        └── SwaggerConfig.java

------------------------------------------------------------------------

## 🧩 Conceptos Clave

### 🔹 Arquitectura Hexagonal

Se divide en **Domain**, **Application** y **Infrastructure**.

-   **Domain:** contiene las reglas del negocio, modelos y puertos.
-   **Application:** implementa los casos de uso (servicios).
-   **Infrastructure:** contiene controladores, adaptadores JPA,
    mappers, config, etc.

### 🔹 Puertos

-   **IN:** lo que la app *puede hacer* (servicios que exponen casos de
    uso).
-   **OUT:** lo que la app *necesita del exterior* (repositorios, APIs
    externas).

### 🔹 Adaptadores

Implemetan los puertos de salida (`RepositoryPort`) usando tecnologías
como **JPA**.

### 🔹 Mappers

Convertimos: - `Entity` ↔ `Domain Model` - Domain Model ↔ DTO (solo si
se usa DTO)

Aquí convertimos: - Entity ↔ Model\
Para mantener el dominio puro.

------------------------------------------------------------------------

## 🧪 Datos Cargados Automáticamente (DataLoader)

``` java
@Component
public class DataLoader {

    private final EventRepositoryPort eventRepo;
    private final VenueRepositoryPort venueRepo;

    public DataLoader(EventRepositoryPort eventRepo, VenueRepositoryPort venueRepo) {
        this.eventRepo = eventRepo;
        this.venueRepo = venueRepo;
    }

    @PostConstruct
    public void init() {

        Venue venue1 = new Venue();
        venue1.setName("Teatro Municipal");
        venue1.setCapacity(500);

        Venue savedVenue = venueRepo.save(venue1);

        Event event1 = new Event();
        event1.setName("Concierto Rock");
        event1.setDate(LocalDate.now().plusDays(10));
        event1.setVenue(savedVenue);

        eventRepo.save(event1);
    }
}
```

------------------------------------------------------------------------

## 📘 Endpoints Implementados

### EVENTOS

  Método   Endpoint                        Descripción
  -------- ------------------------------- ------------------
  POST     `/events`                       Crear evento
  GET      `/events/{id}`                  Buscar por ID
  GET      `/events`                       Listar todos
  GET      `/events/paged?page=0&size=5`   Listado paginado
  PUT      `/events/{id}`                  Actualizar
  DELETE   `/events/{id}`                  Eliminar

### VENUES

  Método   Endpoint          Descripción
  -------- ----------------- ---------------
  POST     `/venues`         Crear venue
  GET      `/venues/{id}`    Buscar por ID
  GET      `/venues`         Listar todos
  GET      `/venues/paged`   Paginado
  PUT      `/venues/{id}`    Actualizar
  DELETE   `/venues/{id}`    Eliminar

------------------------------------------------------------------------

## ⚙️ Requerimientos

-   Java 17+
-   Spring Boot 3
-   MapStruct
-   Spring Data JPA + H2
-   SpringDoc OpenAPI (Swagger)

------------------------------------------------------------------------

## 📄 Cómo levantar el proyecto

``` bash
mvn spring-boot:run
```

Luego accede a Swagger:

    http://localhost:8080/swagger-ui.html

------------------------------------------------------------------------

## 📝 Notas finales

✔ Dominio 100% puro (sin anotaciones de frameworks)\
✔ Adaptadores desacoplados\
✔ Mappers automáticos\
✔ CRUD completo con paginación\
✔ Datos cargados automáticamente\
✔ Proyecto listo para entregar
