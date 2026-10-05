package mx.edu.utez.practica5.controller;

import jakarta.validation.Valid;
import mx.edu.utez.practica5.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.practica5.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.practica5.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/my-services/hospedaje")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping
    public ResponseEntity<ResponseHospedajeDTO> hospedaje(@RequestBody @Valid RequestHospedajeDTO payload) {
        return ResponseEntity.ok(hospedajeService.calcularHospedaje(payload));
    }
}
