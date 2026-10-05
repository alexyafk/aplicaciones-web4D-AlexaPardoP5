package mx.edu.utez.practica5.service;

import mx.edu.utez.practica5.controller.dto.RequestEnvioDTO;
import mx.edu.utez.practica5.controller.dto.ResponseEnvioDTO;
import mx.edu.utez.practica5.exception.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    public ResponseEnvioDTO calcularEnvio(RequestEnvioDTO dto) {
        double volumen = dto.largoCm() * dto.anchoCm() * dto.altoCm();
        if (volumen > 1_000_000) {
            throw new CustomBadRequestException(
                    "No se aceptan paquetes con volumen superior a 1,000,000 cm³");
        }

        double costoBase = 80.0;
        double cargoPeso = 12.0 * dto.pesoKg();
        double cargoVolumen = volumen > 50_000 ? 100.0 : 0.0;
        double acumulado = costoBase + cargoPeso + cargoVolumen;

        double recargoTipoEnvio = switch (dto.tipoEnvio().toUpperCase()) {
            case "ESTANDAR" -> 0.0;
            case "EXPRESS" -> acumulado * 0.40;
            case "MISMO_DIA" -> acumulado * 0.70;
            default -> throw new CustomBadRequestException(
                    "Tipo de envío no válido. Usa: ESTANDAR, EXPRESS o MISMO_DIA");
        };

        double seguro = dto.valorDeclarado() > 10_000 ? dto.valorDeclarado() * 0.02 : 0.0;
        double total = acumulado + recargoTipoEnvio + seguro;

        return new ResponseEnvioDTO(
                redondear(volumen),
                redondear(costoBase),
                redondear(cargoPeso),
                redondear(cargoVolumen),
                redondear(recargoTipoEnvio),
                redondear(seguro),
                redondear(total));
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
