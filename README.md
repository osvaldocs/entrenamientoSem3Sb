# HU3 – Catálogo de Eventos y Venues

**Arquitectura Hexagonal + Spring Boot 3 + Spring Data JPA + Swagger**

Este proyecto implementa la **Historia de Usuario HU3**, donde se refactoriza la aplicación hacia una **arquitectura hexagonal (Ports & Adapters)** manteniendo equivalencia funcional con HU2.
Incluye persistencia con Spring Data JPA (H2), validaciones básicas, documentación con Swagger y estructura por capas desacopladas.

---

## 🚀 Objetivo de la HU3

Refactorizar el catálogo de Eventos y Venues para adoptar una **arquitectura hexagonal**, dividiendo la aplicación en:

* **Domain** – Lógica central del negocio (modelos y reglas).
* **Application** – Casos de uso que orquestan el negocio.
* **Infrastructure** – Adaptadores (Web, JPA, configuración).

La aplicación debe conservar el CRUD funcional de HU2, pero ahora desacoplado mediante **puertos (interfaces) y adaptadores**.

---

## 📂 Estructura del Proyecto (Hexagonal)

```
src/main/java/com.riwi.H3
│
├── domain
│   ├── model
│   │   ├── Event.java
│   │   └── Venue.java
│   └── exception (opcional)
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
│       └── BeanConfig.java
│
└── resources
    ├── application.properties
    └── data.sql (opcional)
```

---

## 🧩 Arquitectura Hexagonal (Resumen)

La app está dividida en 3 anillos:

### **1. Domain (núcleo)**

* Modelos del negocio.
* Sin dependencias a frameworks.
* No importa cómo se guarden o expongan los datos.

### **2. Application**

* Implementa los *Use Cases*.
* Usa puertos **Entrada** (in) y **Salida** (out).
* No conoce JPA, ni HTTP, ni controladores.

---

### **3. Infrastructure**

* Adaptadores concretos:

  * REST Controllers → para exponer la API.
  * JPA Adapters → para conectarse con la BD.
* Repositorios de Spring Data JPA.
* MapStruct (si se usa).
* Configuraciones.

---

## 🗄️ Base de Datos

Se usa **H2 en memoria**, configurado en `application.properties`:

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

Consola H2:

```
http://localhost:8080/h2-console
```

---

## 🛠️ Dependencias principales

Incluye:

* Spring Web
* Spring Data JPA
* H2 Database
* Lombok (opcional)
* Springdoc OpenAPI (Swagger UI)

Swagger se accede en:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📝 Endpoints Principales

### **Events**

| Método | Ruta           | Descripción     |
| ------ | -------------- | --------------- |
| POST   | `/events`      | Crear un evento |
| GET    | `/events`      | Listar todos    |
| GET    | `/events/{id}` | Buscar por ID   |
| PUT    | `/events/{id}` | Actualizar      |
| DELETE | `/events/{id}` | Eliminar        |

### **Venues**

| Método | Ruta           | Descripción    |
| ------ | -------------- | -------------- |
| POST   | `/venues`      | Crear un venue |
| GET    | `/venues`      | Listar todos   |
| GET    | `/venues/{id}` | Buscar por ID  |
| PUT    | `/venues/{id}` | Actualizar     |
| DELETE | `/venues/{id}` | Eliminar       |

---

## 📘 Documentación con Swagger

Los controladores están documentados usando:

```
@Operation
@ApiResponse
@Tag
```

Esto genera una documentación clara agrupada por:

* **Events**
* **Venues**

---

## ✔️ Criterios de Aceptación (HU3)

* CRUD funcional de Eventos y Venues.
* Arquitectura hexagonal implementada.
* Servicios desacoplados por interfaces.
* Adaptadores JPA usando repositorios.
* Documentación Swagger funcionando.
* App ejecutando correctamente en H2.
* Código limpio, organizado y modular.

---

## ▶️ ¿Cómo ejecutar?

```
mvn spring-boot:run
```

Luego visitar:

* Swagger UI: **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**
* H2 Console: **[http://localhost:8080/h2-console](http://localhost:8080/h2-console)**

---

## 👨‍💻 Autor

Proyecto desarrollado como parte del entrenamiento Riwi — HU3: Arquitectura Hexagonal.
