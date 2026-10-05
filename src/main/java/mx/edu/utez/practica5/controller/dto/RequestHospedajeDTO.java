package mx.edu.utez.practica5.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestHospedajeDTO(
        @NotBlank(message = "El nombre del huésped es obligatorio")
        String nombreHuesped,

        @NotBlank(message = "El tipo de habitación es obligatorio")
        String tipoHabitacion,

        @NotNull(message = "El número de noches es obligatorio")
        @Min(value = 1, message = "Debe reservar al menos 1 noche")
        @Max(value = 30, message = "No se aceptan reservaciones de más de 30 noches")
        Integer numeroNoches,

        @NotNull(message = "El número de huéspedes es obligatorio")
        @Min(value = 1, message = "Debe haber al menos 1 huésped")
        Integer numeroHuespedes,

        @NotBlank(message = "La temporada es obligatoria")
        String temporada,

        @NotNull(message = "Debe indicar si incluye desayuno t/f")
        Boolean incluyeDesayuno,

        @NotNull(message = "Debe indicar si incluye estacionamiento t/f")
        Boolean incluyeEstacionamiento
) {}
