package mx.edu.utez.practica5.service;

import mx.edu.utez.practica5.controller.dto.RequestRentaDTO;
import mx.edu.utez.practica5.controller.dto.ResponseRentaDTO;
import mx.edu.utez.practica5.exception.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    public ResponseRentaDTO calcularRenta(RequestRentaDTO dto) {
        String tipo = dto.tipoVehiculo().toUpperCase();
        double costoDiario = switch (tipo) {
            case "COMPACTO" -> 550.0;
            case "SEDAN" -> 700.0;
            case "SUV" -> 950.0;
            case "CAMIONETA" -> 1200.0;
            default -> throw new CustomBadRequestException(
                    "Tipo de vehículo no válido. Usa: COMPACTO, SEDAN, SUV o CAMIONETA");
        };

        if (tipo.equals("CAMIONETA") && dto.edadConductor() < 25) {
            throw new CustomBadRequestException(
                    "No se puede rentar una CAMIONETA a conductores menores de 25 años");
        }

        double costoRenta = costoDiario * dto.diasRenta();
        int kilometrosIncluidos = dto.diasRenta() * 100;
        int kilometrosAdicionales = Math.max(0, dto.kilometrosEstimados() - kilometrosIncluidos);
        double cargoKilometros = kilometrosAdicionales * 4.0;
        double cargoConductorJoven = (dto.edadConductor() >= 18 && dto.edadConductor() <= 24) ? (costoRenta + cargoKilometros) * 0.15 : 0.0;
        double costoSeguro = Boolean.TRUE.equals(dto.seguroCompleto()) ? 180.0 * dto.diasRenta() : 0.0;
        double descuento = dto.diasRenta() >= 7 ? costoRenta * 0.10 : 0.0;
        double total = costoRenta + cargoKilometros + cargoConductorJoven + costoSeguro - descuento;

        return new ResponseRentaDTO(
                redondear(costoRenta),
                kilometrosIncluidos,
                redondear(cargoKilometros),
                redondear(cargoConductorJoven),
                redondear(costoSeguro),
                redondear(descuento),
                redondear(total));
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
