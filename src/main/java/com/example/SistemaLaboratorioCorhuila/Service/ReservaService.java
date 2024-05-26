package com.example.SistemaLaboratorioCorhuila.Service;

import com.example.SistemaLaboratorioCorhuila.Entity.Reserva;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceReserva;
import com.example.SistemaLaboratorioCorhuila.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements IServiceReserva {
    @Autowired
    ReservaRepository reservaRepository;

    @Override
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> obtenerReservasPorFecha(String fecha) {
        return reservaRepository.findByFechaReserva(LocalDate.parse(fecha));
    }

    @Override
    public List<Reserva> obtenerLasReservasActivas() {
        return reservaRepository.findByEstadoIsTrue();
    }

    @Override
    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> guardarReserva(Reserva reserva) {
        try {
            Reserva nuevaReserva = new Reserva();
            nuevaReserva.setUsuario(reserva.getUsuario());
            nuevaReserva.setLaboratorio(reserva.getLaboratorio());
            nuevaReserva.setFechaReserva(reserva.getFechaReserva());
            nuevaReserva.setHoraInicio(reserva.getHoraInicio());
            nuevaReserva.setHoraFin(reserva.getHoraFin());
            nuevaReserva.setEstado(true);
            if (verificarDisponibilidad(nuevaReserva)) {
                reservaRepository.save(nuevaReserva);
                return ResponseEntity.ok("Reserva agregado exitosamente");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El laboratorio no está disponible en el horario solicitado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo realizar la peticion");
        }
    }

    @Override
    public ResponseEntity<String> actualizarReserva(Long id, Reserva reserva) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        if (reservaOptional.isPresent()) {
            Reserva reservaActualizada = new Reserva();
            reservaActualizada.setId(id);
            reservaActualizada.setUsuario(reserva.getUsuario());
            reservaActualizada.setLaboratorio(reserva.getLaboratorio());
            reservaActualizada.setFechaReserva(reserva.getFechaReserva());
            reservaActualizada.setHoraInicio(reserva.getHoraInicio());
            reservaActualizada.setHoraFin(reserva.getHoraFin());
            reservaActualizada.setEstado(true);
            if (verificarDisponibilidad(reservaActualizada)) {
                reservaRepository.save(reservaActualizada);
                return ResponseEntity.ok("Reserva Actualizada correctamente");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El laboratorio no está disponible en el horario solicitado.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Reserva con el ID " + id + " no fue encontrado");
    }

    @Override
    public ResponseEntity<String> eliminarReserva(Long id) {
        Optional<Reserva> reservaEncontrada = reservaRepository.findById(id);
        if (reservaEncontrada.isPresent()) {
            Reserva reservaEliminada = new Reserva();
            reservaEliminada.setId(id);
            reservaEliminada.setUsuario(reservaEncontrada.get().getUsuario());
            reservaEliminada.setLaboratorio(reservaEncontrada.get().getLaboratorio());
            reservaEliminada.setFechaReserva(reservaEncontrada.get().getFechaReserva());
            reservaEliminada.setHoraInicio(reservaEncontrada.get().getHoraInicio());
            reservaEliminada.setHoraFin(reservaEncontrada.get().getHoraFin());
            reservaEliminada.setEstado(false);
            reservaRepository.save(reservaEliminada);
            return ResponseEntity.ok("Reserva Eliminada correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Reserva con el ID " + id + " no fue encontrado");
    }

    private boolean verificarDisponibilidad(Reserva reserva) {
        List<Reserva> reservasExistentes = reservaRepository.buscarPorLaboratorioYfechaReserva(reserva.getLaboratorio().getId(),reserva.getFechaReserva());
        for (Reserva r : reservasExistentes) {
            if (r.getHoraInicio().isBefore(reserva.getHoraFin()) && r.getHoraFin().isAfter(reserva.getHoraInicio())) {
                return false;  // El laboratorio está ocupado en el horario solicitado
            }
        }
        return true;  // El laboratorio está disponible
    }
}
