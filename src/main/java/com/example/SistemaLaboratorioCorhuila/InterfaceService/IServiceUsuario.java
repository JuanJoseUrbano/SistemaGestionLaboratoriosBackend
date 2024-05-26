package com.example.SistemaLaboratorioCorhuila.InterfaceService;

import com.example.SistemaLaboratorioCorhuila.Entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceUsuario {

    List<Usuario> obtenerUsuariosActivos();
    List<Usuario> obtenerPorFiltro(String filtro);
    List<Usuario> obtenerUsuarios();
    Optional<Usuario> obtenerPorId(Long id);
    ResponseEntity<String> registrarUsuario(Usuario usuario);
    ResponseEntity<String> actualizarUsuario(Long id, Usuario usuario);
    ResponseEntity<String> eliminarUsuario(Long id);
}
