package com.example.SistemaLaboratorioCorhuila.Repository;

import com.example.SistemaLaboratorioCorhuila.Entity.TipoLaboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoLaboratorioRepository extends JpaRepository<TipoLaboratorio,Long> {
    List<TipoLaboratorio> findByEstadoIsTrue();
}
