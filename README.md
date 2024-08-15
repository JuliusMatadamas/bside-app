# BSide App

BSide App es una aplicación Java Spring Boot para gestionar información de estudiantes.

## Instalación

1. Asegúrate de tener instalado Java JDK 21 o superior y Maven.
2. Clona este repositorio: `git clone [URL_DEL_REPOSITORIO]`
3. Navega al directorio del proyecto: `cd bsideapp`
4. Instala las dependencias: `mvn install`

## Configuración de la base de datos

La aplicación utiliza PostgreSQL. Asegúrate de tener PostgreSQL instalado y crea una base de datos llamada `bside_app`. Luego, actualiza las credenciales en `src/main/resources/application.properties` si es necesario.

## Ejecución

1. Ejecuta la aplicación: `mvn spring-boot:run`
2. La aplicación estará disponible en `http://localhost:8092`

## Endpoints

### Crear estudiante
- URL: `POST /api/v1/student`
- Cuerpo de la solicitud en formato JSON:
{
    "firstName": "Nombre",
    "lastName": "Apellido",
    "birthdate": "YYYY-MM-DD",
    "genre": "M" o "F",
    "email": "correo@ejemplo.com"
}

### Actualizar estudiante
- URL: `PUT /api/v1/student`
- Cuerpo de la solicitud en formato JSON:
{
    "id":1
    "firstName": "Nombre",
    "lastName": "Apellido",
    "birthdate": "YYYY-MM-DD",
    "genre": "M" o "F",
    "email": "correo@ejemplo.com"
}

### Obtener todos los estudiantes
- URL: `GET /api/v1/students`

### Obtener estudiante por su id
- URL: `GET /api/v1/student/{id}`

### Eliminar estudiante por su id
- URL: `DELETE /api/v1/student/{id}`

### Buscar estudiantes por nombre
- URL: `GET /api/v1/students/firstname/{firstName}`

### Buscar estudiantes por apellido
- URL: `GET /api/v1/students/lastname/{lastName}`

### Buscar estudiantes por género
- URL: `GET /api/v1/students/genre/{genre}`

### Buscar estudiantes por email
- URL: `GET /api/v1/students/email/{email}`

### Buscar estudiantes por edad mínima
- URL: `GET /api/v1/students/age/{minimunAge}`

### Respuestas
- Todas las respuestas siguen el formato:
{
    "message":"Mensaje de la operación"
    "data": [
        {
            // Datos del estudiante o estudiantes
        }
    ]
}

### Pruebas
- Pruebas del Servicio
- Navegar al directorio de pruebas: cd src/test/java/com/jm/bsideapp/service/implementation
- Ejecuta las pruebas: mvn test -Dtest=StudentImplementationTest

- Pruebas del Controlador
- Navegar al directorio de pruebas: cd src/test/java/com/jm/bsideapp/controller
- Ejecuta las pruebas: mvn test -Dtest=StudentControllerTest


