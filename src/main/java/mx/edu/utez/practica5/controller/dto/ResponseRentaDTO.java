package mx.edu.utez.practica5.controller.dto;

public record ResponseRentaDTO(
        double costoRenta,
        int kilometrosIncluidos,
        double cargoKilometrosAdicionales,
        double cargoConductorJoven,
        double costoSeguro,
        double descuento,
        double total
) {}
