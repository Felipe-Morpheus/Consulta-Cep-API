package com.consulta.Consulta_cep_api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    /*informando ao Spring que ele deve gerenciar o RestTemplate como um bean e assim poder usa-lo.*/
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
