package mx.edu.utez.practica5.service;

import mx.edu.utez.practica5.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.practica5.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.practica5.exception.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public ResponseHospedajeDTO calcularHospedaje(RequestHospedajeDTO dto) {
        String tipo = dto.tipoHabitacion().toUpperCase();
        String temporada = dto.temporada().toUpperCase();

        double costoPorNoche = switch (tipo) {
            case "INDIVIDUAL" -> 700.0;
            case "DOBLE" -> 1100.0;
            case "SUITE" -> 1800.0;
            default -> throw new CustomBadRequestException(
                    "Tipo de habitación no válido. Usa: INDIVIDUAL, DOBLE o SUITE");
        };

        int capacidad = switch (tipo) {
            case "INDIVIDUAL" -> 1;
            case "DOBLE" -> 2;
            default -> 4;
        };

        if (dto.numeroHuespedes() > capacidad) {
            throw new CustomBadRequestException(
                    "La habitación " + tipo.toLowerCase() + " solo permite " + capacidad
                            + (capacidad == 1 ? " huésped" : " huéspedes"));
        }

        double costoHospedaje = costoPorNoche * dto.numeroNoches();
        double descuentoTemporada = 0.0;
        double cargoTemporada = 0.0;
        switch (temporada) {
            case "BAJA" -> descuentoTemporada = costoHospedaje * 0.10;
            case "REGULAR" -> { }
            case "ALTA" -> cargoTemporada = costoHospedaje * 0.25;
            default -> throw new CustomBadRequestException(
                    "Temporada no válida. Usa: BAJA, REGULAR o ALTA");
        }

        double costoDesayuno = Boolean.TRUE.equals(dto.incluyeDesayuno())? dto.numeroHuespedes() * dto.numeroNoches() * 150.0 : 0.0;
        double costoEstacionamiento = Boolean.TRUE.equals(dto.incluyeEstacionamiento()) ? dto.numeroNoches() * 100.0 : 0.0;
        double descuentoEstanciaLarga = dto.numeroNoches() >= 7 ? costoHospedaje * 0.08 : 0.0;
        double subtotal = costoHospedaje - descuentoTemporada + cargoTemporada - descuentoEstanciaLarga + costoDesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        return new ResponseHospedajeDTO(
                redondear(costoHospedaje),
                redondear(descuentoTemporada),
                redondear(cargoTemporada),
                redondear(descuentoEstanciaLarga),
                redondear(costoDesayuno),
                redondear(costoEstacionamiento),
                redondear(subtotal),
                redondear(impuesto),
                redondear(total));
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
