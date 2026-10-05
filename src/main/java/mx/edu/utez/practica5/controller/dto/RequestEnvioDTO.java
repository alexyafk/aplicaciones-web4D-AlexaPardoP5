package mx.edu.utez.practica5.controller.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record RequestEnvioDTO(
        @NotBlank(message = "El código postal es obligatorio")
        String codigoPostal,

        @NotNull(message = "El peso es obligatorio")
        @Positive(message = "El peso debe ser mayor a 0 kg")
        @DecimalMax(value = "50", message = "No se aceptan paquetes de más de 50 kg")
        Double pesoKg,

        @NotNull(message = "El largo es obligatorio")
        @Positive(message = "El largo debe ser mayor a 0 cm")
        @DecimalMax(value = "150", message = "El largo no puede ser mayor a 150 cm")
        Double largoCm,

        @NotNull(message = "El ancho es obligatorio")
        @Positive(message = "El ancho debe ser mayor a 0 cm")
        @DecimalMax(value = "150", message = "El ancho no puede ser mayor a 150 cm")
        Double anchoCm,

        @NotNull(message = "El alto es obligatorio")
        @Positive(message = "El alto debe ser mayor a 0 cm")
        @DecimalMax(value = "150", message = "El alto no puede ser mayor a 150 cm")
        Double altoCm,

        @NotBlank(message = "El tipo de envío es obligatorio")
        String tipoEnvio,

        @NotNull(message = "El valor declarado es obligatorio")
        @PositiveOrZero(message = "El valor declarado no puede ser negativo")
        Double valorDeclarado
) {}
