API REST - Ferretería Ferremás

Este proyecto consiste en una API RESTful desarrollada en Java con Spring Boot que permite gestionar productos y sucursales para la cadena de ferreterías Ferremás. La aplicación cumple con los requerimientos definidos en el caso práctico del ramo Integración de Plataformas.

----------------------------------------------------
Requisitos para ejecutar

- Java 17 o superior (funciona con Java 24)
- Maven
- Visual Studio Code (u otro IDE compatible)
- Extensiones Java y Spring Boot en VS Code (opcional)
- Postman para pruebas

----------------------------------------------------
¿Cómo ejecutar la API localmente?

1. Clona o descarga el proyecto.

2. Abre la carpeta del proyecto en Visual Studio Code.

3. En la terminal, ejecuta el siguiente comando para iniciar la aplicación:

   ./mvnw spring-boot:run

   En Windows:
   mvnw.cmd spring-boot:run

4. Una vez iniciada, la API estará disponible en:
   http://localhost:8080

----------------------------------------------------
Endpoints disponibles

Método | Endpoint                        | Descripción
-------|----------------------------------|--------------------------------------------------
GET    | /productos                      | Listar todos los productos (con filtros opcionales)
GET    | /productos/{id}                 | Obtener un producto por su ID
POST   | /productos                      | Crear un nuevo producto
PUT    | /productos/{id}                 | Actualizar completamente un producto
PATCH  | /productos/{id}                 | Actualizar parcialmente un producto
DELETE | /productos/{id}                 | Eliminar un producto
GET    | /sucursales                     | Listar todas las sucursales
POST   | /sucursales                     | Crear una nueva sucursal
GET    | /sucursales/{id}/productos      | Listar productos disponibles en una sucursal

----------------------------------------------------
Colección Postman

Se incluye una colección Postman para probar todos los endpoints.

Ruta del archivo:
  /Users/danielavila/Tareas duoc/API Ferremas.postman_collection.json

¿Cómo usarla?
1. Abre Postman
2. Ve a File > Import
3. Selecciona el archivo .json exportado
4. Abre la colección API Ferremás y prueba los endpoints

----------------------------------------------------
Estado del proyecto

✔️ Todos los endpoints implementados  
✔️ Filtros combinables en /productos  
✔️ Manejo de errores y códigos HTTP  
✔️ Colección Postman con pruebas exitosas y casos con error (404, 400, etc.)

----------------------------------------------------
Autor

Daniel Ávila – Duoc UC – Integración de Plataformas
