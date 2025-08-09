package com.consulta.Consulta_cep_api.Controller;

import com.consulta.Consulta_cep_api.Entity.Registro;
import com.consulta.Consulta_cep_api.Service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Registro registro = service.consultarCep(cep);
        return ResponseEntity.ok(registro);
    }

    @PostMapping
    public ResponseEntity<Registro> salvarRegistro(@RequestBody Registro registro) {
        Registro salvo = service.salvarRegistro(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }



}