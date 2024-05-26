package com.example.SistemaLaboratorioCorhuila.Repository;

import com.example.SistemaLaboratorioCorhuila.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol,Long> {
    List<Rol> findByEstadoIsTrue();
}
