package com.example.SistemaLaboratorioCorhuila.Controller;

import com.example.SistemaLaboratorioCorhuila.Entity.Reserva;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceReserva;
import com.example.SistemaLaboratorioCorhuila.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/laboratorio-corhuila/reserva")
public class ReservaController {
    @Autowired
    IServiceReserva iServiceReserva;

    @GetMapping("/encontrar-todos")
    public List<Reserva> obtener() {
        return iServiceReserva.obtenerTodasLasReservas();
    }

    @GetMapping("/encontrar-activas")
    public List<Reserva> disponibles() {
        return iServiceReserva.obtenerLasReservasActivas();
    }

    @GetMapping("/busqueda")
    public List<Reserva> busqueda(@Param("filtro") String filtro) {
        return iServiceReserva.obtenerReservasPorFecha(filtro);
    }


    @GetMapping("/encontrar-por/{id}")
    public Optional<Reserva> obtenerPorId(@PathVariable Long id) {
        return iServiceReserva.obtenerReservaPorId(id);
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody(required = true) Reserva reserva) {
        return iServiceReserva.guardarReserva(reserva);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Reserva reserva) {
        return iServiceReserva.actualizarReserva(id, reserva);
    }

    @PutMapping("/eliminar-por/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable Long id) {
        return iServiceReserva.eliminarReserva(id);
    }

}
