# Práctica 5 – Servicios REST con Spring Boot

Tres cotizadores: **Envío**, **Renta de vehículos** y **Hospedaje**.
Java 21 · Spring Boot 4.1.1 · Puerto **8080**.

## Requisitos de la práctica
- DTO de entrada con `@Valid` y anotaciones de validación
- `ResponseEntity` en todas las respuestas
- `@RestControllerAdvice` (`ErrorHandler`) + excepción personalizada (`CustomBadRequestException`)
- Separación de responsabilidades: `Controller` → `Service`

## Estructura
```
controller/       EnvioController, RentaController, HospedajeController
controller/dto/   Request*/Response* de cada servicio
service/          EnvioService, RentaService, HospedajeService
exception/        CustomBadRequestException, ErrorHandler
```

## Correr
```bash
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```
Swagger UI: http://localhost:8080/swagger-ui.html

## Endpoints (todos POST con body JSON)

### `POST /my-services/envio`
```json
{"codigoPostal":"62577","pesoKg":8.5,"largoCm":40,"anchoCm":30,"altoCm":25,"tipoEnvio":"EXPRESS","valorDeclarado":12000}
```
Respuesta esperada: `costoTotal` = **494.8** (80 + 102 de peso = 182, +40 % EXPRESS = 254.8, + 240 de seguro).

### `POST /my-services/renta`
```json
{"nombreCliente":"Ana Martínez","edadConductor":24,"tipoVehiculo":"SUV","diasRenta":5,"kilometrosEstimados":650,"seguroCompleto":true}
```
Respuesta esperada: `total` = **7052.5** (4750 renta + 600 km extra + 802.5 cargo por edad + 900 seguro).

### `POST /my-services/hospedaje`
```json
{"nombreHuesped":"Carlos López","tipoHabitacion":"DOBLE","numeroNoches":4,"numeroHuespedes":2,"temporada":"ALTA","incluyeDesayuno":true,"incluyeEstacionamiento":true}
```
Respuesta esperada: `total` = **7384.0** (subtotal 7100 + 4 % de impuesto).

## Errores (siempre JSON con código 400)
```json
{"numeroNoches":"No se aceptan reservaciones de más de 30 noches"}
{"error":"La habitación individual solo permite 1 huésped"}
{"error":"No se puede rentar una CAMIONETA a conductores menores de 25 años"}
```
- Validaciones de campo (`@NotNull`, `@Min`, `@Max`, ...) → `MethodArgumentNotValidException` → mapa campo/mensaje.
- Reglas de negocio rotas en el Service → `CustomBadRequestException` → `{"error": "..."}`.
