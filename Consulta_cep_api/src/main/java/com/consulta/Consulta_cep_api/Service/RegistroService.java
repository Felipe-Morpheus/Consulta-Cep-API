package com.consulta.Consulta_cep_api.Service;

import com.consulta.Consulta_cep_api.Entity.CepReceptor;
import com.consulta.Consulta_cep_api.Entity.Registro;
import com.consulta.Consulta_cep_api.Repository.RegistroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class RegistroService {

    private final RegistroRepository repository;
    private final RestTemplate restTemplate;
    //private final String BASE_URL = "https://viacep.com.br/ws/"; // Linka a API de CEP
    private final String BASE_URL = "http://localhost:3000/ws/"; // Linka o Teste do Mockoon


    public RegistroService(RegistroRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    /*Função*/
    public Registro consultarCep(String cep) {
        // Limpa o CEP
        cep = cep.replaceAll("[^0-9]", "");

        // Consulta a API
        String url = BASE_URL + cep + "/json/";
       // String url = BASE_URL + cep; //Mockoon

        CepReceptor cepReceptor = restTemplate.getForObject(url, CepReceptor.class);

        // Verifica se deu erro
        if (cepReceptor == null || Boolean.TRUE.equals(cepReceptor.getErro())) {
            System.err.println("CEP inválido ou não encontrado: " + cep);
            return null;
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

        return repository.saveAndFlush(registro);
    }

}

