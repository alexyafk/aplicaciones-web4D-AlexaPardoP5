package mx.edu.utez.practica5.controller;

import jakarta.validation.Valid;
import mx.edu.utez.practica5.controller.dto.RequestEnvioDTO;
import mx.edu.utez.practica5.controller.dto.ResponseEnvioDTO;
import mx.edu.utez.practica5.service.EnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/my-services/envio")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<ResponseEnvioDTO> envio(@RequestBody @Valid RequestEnvioDTO payload) {
        return ResponseEntity.ok(envioService.calcularEnvio(payload));
    }
}
