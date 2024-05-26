package com.example.SistemaLaboratorioCorhuila.InterfaceService;

import com.example.SistemaLaboratorioCorhuila.Entity.Reserva;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceReserva {
    List<Reserva> obtenerTodasLasReservas();
    List<Reserva> obtenerReservasPorFecha(String fecha);
    List<Reserva> obtenerLasReservasActivas();
    Optional<Reserva> obtenerReservaPorId(Long id);
    ResponseEntity<String> guardarReserva(Reserva reserva);
    ResponseEntity<String> actualizarReserva(Long id, Reserva reserva);
    ResponseEntity<String> eliminarReserva(Long id);
}
