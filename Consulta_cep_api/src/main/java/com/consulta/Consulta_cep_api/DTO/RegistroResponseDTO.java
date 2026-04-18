package com.consulta.Consulta_cep_api.DTO;

public record RegistroResponseDTO(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {}
