package com.example.SistemaLaboratorioCorhuila.Repository;

import com.example.SistemaLaboratorioCorhuila.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByEstadoIsTrue();
    List<Reserva> findByFechaReserva(LocalDate fechaReserva);
    @Query(value = "SELECT * FROM reservas WHERE laboratorio_id = :laboratorio_id AND fecha_reserva = :fecha_reserva", nativeQuery = true)
    List<Reserva> buscarPorLaboratorioYfechaReserva(@Param("laboratorio_id") Long laboratorioId, @Param("fecha_reserva") LocalDate fechaReserva);
}
