package mx.edu.utez.practica5.controller;

import jakarta.validation.Valid;
import mx.edu.utez.practica5.controller.dto.RequestRentaDTO;
import mx.edu.utez.practica5.controller.dto.ResponseRentaDTO;
import mx.edu.utez.practica5.service.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/my-services/renta")
public class RentaController {

    private final RentaService rentaService;

    public RentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

    @PostMapping
    public ResponseEntity<ResponseRentaDTO> renta(@RequestBody @Valid RequestRentaDTO payload) {
        return ResponseEntity.ok(rentaService.calcularRenta(payload));
    }
}
