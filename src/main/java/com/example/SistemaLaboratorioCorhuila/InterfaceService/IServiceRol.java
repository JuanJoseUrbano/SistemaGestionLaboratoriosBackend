package com.example.SistemaLaboratorioCorhuila.InterfaceService;

import com.example.SistemaLaboratorioCorhuila.Entity.Rol;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceRol {
    List<Rol> obtenerRoles();
    Optional<Rol> obtenerPorId(Long id);
    ResponseEntity<String> registrarRol(Rol Rol);
    ResponseEntity<String> actualizarRol(Long id, Rol rol);
    ResponseEntity<String> eliminarRol(Long id);
}
