package com.example.SistemaLaboratorioCorhuila.Repository;

import com.example.SistemaLaboratorioCorhuila.Entity.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LaboratorioRepository extends JpaRepository<Laboratorio,Long> {
    List<Laboratorio> findByEstadoIsTrue();
    @Query("SELECT l FROM Laboratorio l WHERE (l.nombre LIKE %:filtro% OR l.ubicacion LIKE %:filtro%) AND l.estado = true")
    List<Laboratorio> findByNombreOrUbicacion(String filtro);

}
