package com.consulta.Consulta_cep_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com/consulta/Consulta_cep_api/Entity")
@SpringBootApplication
public class ConsultaCepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaCepApiApplication.class, args);
	}

}
