# Anime API - Catálogo
Esta es una API RESTful diseñada para la gestión integral de animes, sus categorías y sus estudios de animación.

# Características
* **Arquitectura en capas:** Separación clara de responsabilidades en Modelos, Repositorios, Servicios y Controladores.
* **Manejo global de excepciones:** Errores centralizados mediante `@ControllerAdvice`, garantizando respuestas en formato JSON claras.
* **Borrado lógico (Soft Delete):** Implementación de integridad de datos donde la información no se elimina físicamente de la base de datos, sino que se marca como inactiva.
* **Integración de Swagger:** Documentación interactiva para probar los endpoints en tiempo real.

# Tecnologías utilizadas
* **Java 21:** Lenguaje de programación principal (JDK 21).
* **Spring Boot 3:** Framework base para el desarrollo.
* **Spring Data JPA:** Abstracción para la persistencia y acceso a datos.
* **PostgreSQL:** Motor de base de datos relacional.
* **Lombok:** Biblioteca para la reducción de código repetitivo (Boilerplate).
* **Swagger/OpenAPI:** Herramienta para documentación y testing.

# Estructura del Proyecto
```text
└── src
    └── main
        └── java/com/...
            ├── controller/   # Gestión de Endpoints y validación de entrada (@Valid).
            ├── service/      # Capa de lógica de negocio y validaciones.
            ├── repository/   # Interfaz de acceso a la base de datos (JPA).
            ├── model/        # Entidades del dominio y Enums.
            ├── exception/    # Manejador de excepciones personalizadas.
            └── AnimeApiApplication.java
```

# Instalación

## Prerrequisitos
1. **JDK 21** o superior
2. **Maven** instalado
3. **PostgreSQL** instalado y corriendo

## Cómo ejecutar

1. **Clonar el repositorio:**
```bash
git clone https://github.com/carloshuarcayah/anime-api.git
```

2. **Configurar la base de datos:**
   * Crea una base de datos en PostgreSQL
   * Renombra el archivo `src/main/resources/application.properties.example` a `application.properties`
   * Actualiza las credenciales en `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/TU_BASE_DE_DATOS
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
```

3. **Ejecutar la aplicación:**

   **Opción 1: Desde un IDE**
   * Abre el proyecto en tu IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)
   * Haz clic en el botón de ejecutar

   **Opcion 2: Desde la terminal**
```bash
   mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

# Swagger
Una vez iniciada la aplicación, puedes acceder a la documentación y probar la API en:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

**Autor:** Yo -  (Estudiante) Desarrollador Backend en formación
