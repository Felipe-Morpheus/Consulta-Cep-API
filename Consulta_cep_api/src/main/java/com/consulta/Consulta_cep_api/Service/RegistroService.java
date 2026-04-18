package com.consulta.Consulta_cep_api.Service;

import com.consulta.Consulta_cep_api.DTO.RegistroResponseDTO;
import com.consulta.Consulta_cep_api.Entity.CepReceptor;
import com.consulta.Consulta_cep_api.Entity.Registro;
import com.consulta.Consulta_cep_api.Exception.CepNaoEncontradoException;
import com.consulta.Consulta_cep_api.Repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Service
public class RegistroService {

    private final RegistroRepository repository;
    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RegistroService.class);


    @Value("${cep.api.base-url}")
    private String baseUrl;

    public RegistroService(RegistroRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    /*Função*/
    public RegistroResponseDTO consultarCep(String cep) {
        // Limpa o CEP
        cep = cep.replaceAll("[^0-9]", "");

        // Consulta a API
       String url = baseUrl + cep + "/json/";
       logger.info("Consultando CEP: {}", cep);

        CepReceptor cepReceptor = restTemplate.getForObject(url, CepReceptor.class);

        // Verifica se deu erro
        if (cepReceptor == null || Boolean.TRUE.equals(cepReceptor.getErro())) {
            throw new CepNaoEncontradoException("CEP inválido ou não encontrado: " + cep);

        }



        // Cria e salva o registro
        Registro registro = new Registro();
        registro.setCep(cep);
        registro.setDataConsulta(LocalDateTime.now());
        registro.setLogradouro(cepReceptor.getLogradouro());
        registro.setComplemento(cepReceptor.getComplemento());
        registro.setBairro(cepReceptor.getBairro());
        registro.setLocalidade(cepReceptor.getLocalidade());
        registro.setUf(cepReceptor.getUf());

        logger.info("Salvando registro:{}", registro);

        Registro salvo = repository.saveAndFlush(registro);
        return new RegistroResponseDTO(
                salvo.getCep(),
                salvo.getLogradouro(),
                salvo.getBairro(),
                salvo.getLocalidade(),
                salvo.getUf()
        );
    }



}

