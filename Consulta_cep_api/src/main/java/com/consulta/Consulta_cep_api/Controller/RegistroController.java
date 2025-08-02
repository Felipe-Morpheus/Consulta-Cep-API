package com.consulta.Consulta_cep_api.Controller;

import com.consulta.Consulta_cep_api.Entity.Registro;
import com.consulta.Consulta_cep_api.Service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
public class RegistroController {

    private final RegistroService service;

    @Autowired
    public RegistroController(RegistroService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Registro> consultarCep(@PathVariable String cep) {
        try {
            Registro registro = service.consultarCep(cep);

            if (registro == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(registro);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}