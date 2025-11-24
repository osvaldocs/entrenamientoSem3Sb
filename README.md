HU3 – Catálogo de Eventos y Venues

Arquitectura Hexagonal + Spring Boot 3 + Spring Data JPA + Swagger + Paginación

Este proyecto implementa la Historia de Usuario HU3, donde se refactoriza la aplicación hacia una arquitectura hexagonal (Ports & Adapters) manteniendo equivalencia funcional con HU2.
Incluye persistencia con Spring Data JPA (H2), validaciones, documentación con Swagger, paginación y una estructura completamente desacoplada.

🚀 Objetivo de la HU3

Refactorizar el catálogo de Eventos y Venues para adoptar una arquitectura hexagonal, dividiendo la aplicación en:

Domain – Lógica central del negocio (modelos y reglas).

Application – Casos de uso que orquestan el negocio.

Infrastructure – Adaptadores (Web, JPA, configuración).

La aplicación debe conservar el CRUD funcional de HU2, ahora con persistencia real, paginación y una arquitectura altamente mantenible.

🔄 Paginación (Nueva sección agregada)

La HU3 requiere que el endpoint de listar Eventos soporte paginación con Spring Data JPA.

Esto está implementado en el método:
