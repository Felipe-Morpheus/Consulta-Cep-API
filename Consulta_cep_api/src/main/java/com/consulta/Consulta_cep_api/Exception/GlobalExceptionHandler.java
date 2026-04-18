package com.consulta.Consulta_cep_api.Exception;

import com.consulta.Consulta_cep_api.Exception.CepNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CepNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleCepNaoEncontrado(CepNaoEncontradoException ex) {

        logger.warn("CEP inválido: {}", ex.getMessage());

        Map<String, Object> erro = new HashMap<>();
        erro.put("status", 404);
        erro.put("mensagem", ex.getMessage());
        erro.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}