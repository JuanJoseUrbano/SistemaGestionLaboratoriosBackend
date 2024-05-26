package com.example.SistemaLaboratorioCorhuila.InterfaceService;

import com.example.SistemaLaboratorioCorhuila.Entity.Laboratorio;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceLaboratorio {
    List<Laboratorio> obtenerLaboratorios();
    List<Laboratorio> obtenerPorBusqueda(String filtro);
    Optional<Laboratorio> obtenerPorId(Long id);
    ResponseEntity<String> registrarLaboratorio(Laboratorio Laboratorio);
    ResponseEntity<String> actualizarLaboratorio(Long id, Laboratorio Laboratorio);
    ResponseEntity<String> eliminarLaboratorio(Long id);
}
