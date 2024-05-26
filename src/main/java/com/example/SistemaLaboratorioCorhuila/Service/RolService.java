package com.example.SistemaLaboratorioCorhuila.Service;

import com.example.SistemaLaboratorioCorhuila.Entity.Rol;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceRol;
import com.example.SistemaLaboratorioCorhuila.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IServiceRol {
    @Autowired
    RolRepository rolRepository;
    @Override
    public List<Rol> obtenerRoles() {
        return rolRepository.findByEstadoIsTrue();
    }

    @Override
    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> registrarRol(Rol rol) {
        rolRepository.save(new Rol(rol.getId(),rol.getNombre(),true,null));
        return ResponseEntity.ok("Rol agregado corectamente");
    }

    @Override
    public ResponseEntity<String> actualizarRol(Long id, Rol rol) {
        Optional<Rol> rolBuscado = rolRepository.findById(id);
        if(rolBuscado.isPresent()){
            rolRepository.save(new Rol(id,rol.getNombre(),true, null));
            return ResponseEntity.ok("Rol Actualizado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El rol con el ID " + id + " no fue encontrado");
    }

    @Override
    public ResponseEntity<String> eliminarRol(Long id) {
        Optional<Rol> rolBuscado = rolRepository.findById(id);
        if(rolBuscado.isPresent()){
            rolRepository.save(new Rol(id,rolBuscado.get().getNombre(),false, LocalDateTime.now()));
            return ResponseEntity.ok("Rol Eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El rol con el ID " + id + " no fue encontrado");
    }
}
