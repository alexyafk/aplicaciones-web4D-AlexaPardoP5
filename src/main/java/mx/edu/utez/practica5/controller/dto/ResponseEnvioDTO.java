package mx.edu.utez.practica5.controller.dto;

public record ResponseEnvioDTO(
        double volumenCm3,
        double costoBase,
        double cargoPeso,
        double cargoVolumen,
        double recargoTipoEnvio,
        double seguro,
        double costoTotal
) {}
