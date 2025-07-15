# Sales Management System

Sistema de gestión de ventas desarrollado con **Java 21**, **Spring Boot 3.2.5**, **JPA**, **MySQL** y **Swagger OpenAPI**. Permite la administración de clientes, productos, direcciones y recibos de venta.

## Tecnologías utilizadas

* Java 21
* Spring Boot 3.2.5
* Spring Data JPA
* Spring Validation
* MySQL / H2 (modo runtime)
* Lombok
* Swagger UI (SpringDoc OpenAPI 2.5.0)
* Maven

## Endpoints principales

### Clientes

* `GET /api/clients`: Obtener todos los clientes.
* `GET /api/clients/{id}`: Obtener cliente por ID.
* `POST /api/clients/create`: Crear nuevo cliente.
* `PUT /api/clients/{id}`: Actualizar cliente completo.
* `PATCH /api/clients/{id}`: Actualización parcial.
* `DELETE /api/clients/{id}`: Eliminar cliente.

### Productos

* `GET /api/products`: Listar productos.
* `GET /api/products/{id}`: Obtener producto por ID.
* `POST /api/products`: Crear nuevo producto.
* `PUT /api/products/{id}`: Actualizar producto completo.
* `PATCH /api/products/{id}`: Actualización parcial.
* `DELETE /api/products/{id}`: Eliminar producto.

### Recibos

* `GET /api/receipts`: Listar todos los recibos.
* `GET /api/receipts/{id}`: Obtener recibo por ID.
* `POST /api/receipts`: Crear nuevo recibo.
* `PUT /api/receipts/{id}`: Marcar recibo como devuelto.
* Filtros disponibles por fecha y total.

### Direcciones

* `GET /api/addresses`: Listar todas las direcciones.
* `GET /api/clients/{id}/addresses`: Direcciones por cliente.
* `POST /api/clients/{id}/addresses`: Crear dirección para cliente.
* `PUT /api/addresses/{id}`: Actualizar dirección.
* `DELETE /api/addresses/{id}`: Eliminar dirección.

## Estructura del proyecto

```text
com.salesmanagement.system
├── controllers
├── DTO
├── entities
├── exceptions
├── repositories
├── responses
├── services
└── SalesManagementSystemApplication.java
```

## Base de datos

* **Desarrollo:** H2 embebido (`/h2-console`)
* **Producción:** MySQL (`jdbc:mysql://localhost:3306/sales_java`)

## Documentación Swagger

* UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Documentación para Postman

Podés importar los endpoints en Postman desde el archivo JSON incluido en el proyecto:

📄 `src/main/resources/docs/api-endpoints.json`

### Instrucciones para importar en Postman

1. Abrí Postman
2. Clic en **"Import"**
3. Seleccioná la pestaña **"File"**
4. Subí el archivo `api-endpoints.json`
5. ¡Listo! Tendrás todos los endpoints cargados y listos para probar

> También podés importar directamente desde: `http://localhost:8080/v3/api-docs`

## Cómo ejecutar

1. Clonar el repositorio
2. Configurar la base de datos (ver `application.properties`)
3. Ejecutar con:

```bash
./mvnw spring-boot:run
```

O desde IntelliJ: `Run > SalesManagementSystemApplication`

## Notas

* Se utilizan DTOs para desacoplar la lógica interna.
* Validaciones con anotaciones (`@NotNull`, `@Min`, etc.).
* Se implementó manejo global de excepciones con `@ControllerAdvice`.
* Se agregó soporte para actualizaciones parciales con `PATCH`.

---

