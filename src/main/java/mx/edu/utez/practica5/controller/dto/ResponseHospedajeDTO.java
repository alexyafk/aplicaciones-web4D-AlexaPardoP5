package mx.edu.utez.practica5.controller.dto;

public record ResponseHospedajeDTO(
        double costoHospedaje,
        double descuentoTemporada,
        double cargoTemporada,
        double descuentoEstanciaLarga,
        double costoDesayuno,
        double costoEstacionamiento,
        double subtotal,
        double impuesto,
        double total
) {}
