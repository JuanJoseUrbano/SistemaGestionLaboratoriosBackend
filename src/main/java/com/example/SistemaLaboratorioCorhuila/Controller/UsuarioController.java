package com.example.SistemaLaboratorioCorhuila.Controller;

import com.example.SistemaLaboratorioCorhuila.Entity.Rol;
import com.example.SistemaLaboratorioCorhuila.Entity.Usuario;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/laboratorio-corhuila/usuario")
public class UsuarioController {
    @Autowired
    IServiceUsuario iServiceUsuario;

    @GetMapping("/encontrar-todos")
    public List<Usuario> obtener() {
        return iServiceUsuario.obtenerUsuarios();
    }

    @GetMapping("/encontrar-activos")
    public List<Usuario> obtenerActivos() {
        return iServiceUsuario.obtenerUsuariosActivos();
    }

    @GetMapping("/busqueda")
    public List<Usuario> busqueda(@Param("filtro") String filtro) {
        return iServiceUsuario.obtenerPorFiltro(filtro);
    }

    @GetMapping("/encontrar-por/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable Long id) {
        return iServiceUsuario.obtenerPorId(id);
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody(required = true) Usuario user) {
        return iServiceUsuario.registrarUsuario(user);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Usuario user) {
        return iServiceUsuario.actualizarUsuario(id, user);
    }

    @PutMapping("/eliminar-por/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable Long id) {
        return iServiceUsuario.eliminarUsuario(id);
    }
}
