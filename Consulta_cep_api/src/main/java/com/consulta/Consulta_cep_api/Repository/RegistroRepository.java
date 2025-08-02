package com.consulta.Consulta_cep_api.Repository;

import com.consulta.Consulta_cep_api.Entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
}
