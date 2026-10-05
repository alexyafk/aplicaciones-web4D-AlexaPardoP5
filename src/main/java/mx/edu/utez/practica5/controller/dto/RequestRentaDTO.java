package mx.edu.utez.practica5.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record RequestRentaDTO(
        @NotBlank(message = "El nombre del cliente es obligatorio")
        String nombreCliente,

        @NotNull(message = "La edad del conductor es obligatoria")
        @Min(value = 18, message = "No se acepta la renta a conductores menores de 18 años")
        Integer edadConductor,

        @NotBlank(message = "El tipo de vehículo es obligatorio ")
        String tipoVehiculo,

        @NotNull(message = "El número de días de renta es obligatorio")
        @Min(value = 1, message = "Debe rentar al menos 1 día")
        @Max(value = 30, message = "No se acepta una renta mayor a 30 días")
        Integer diasRenta,

        @NotNull(message = "Los kilómetros estimados son obligatorios")
        @PositiveOrZero(message = "Los kilómetros estimados no pueden ser negativos")
        @Max(value = 5000, message = "No se aceptan más de 5,000 kilómetros estimados")
        Integer kilometrosEstimados,

        @NotNull(message = "Debe indicar si contrata el seguro completo t/f")
        Boolean seguroCompleto
) {}
