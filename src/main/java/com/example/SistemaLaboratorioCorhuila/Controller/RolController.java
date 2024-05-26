package com.example.SistemaLaboratorioCorhuila.Controller;

import com.example.SistemaLaboratorioCorhuila.Entity.Rol;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/laboratorio-corhuila/rol")
public class RolController {
    @Autowired
    private IServiceRol iServiceRoles;

    @GetMapping("/encontrar-todos")
    public List<Rol> obtener() {
        return iServiceRoles.obtenerRoles();
    }

    @GetMapping("/encontrar-por/{id}")
    public Optional<Rol> obtenerPorId(@PathVariable Long id) {
        return iServiceRoles.obtenerPorId(id);
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody(required = true) Rol rol) {
        return iServiceRoles.registrarRol(rol);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Rol rol) {
        return iServiceRoles.actualizarRol(id, rol);
    }

    @PutMapping("/eliminar-por/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable Long id) {
        return iServiceRoles.eliminarRol(id);
    }
}
