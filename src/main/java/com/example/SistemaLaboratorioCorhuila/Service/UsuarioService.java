package com.example.SistemaLaboratorioCorhuila.Service;

import com.example.SistemaLaboratorioCorhuila.Entity.Usuario;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceUsuario;
import com.example.SistemaLaboratorioCorhuila.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService implements IServiceUsuario {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> obtenerUsuariosActivos() {
        return usuarioRepository.findByEstadoIsTrue();
    }

    @Override
    public List<Usuario> obtenerPorFiltro(String filtro) {
        return usuarioRepository.buscarPorNombresCorreoTelefono(filtro);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return  usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return  usuarioRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> registrarUsuario(Usuario usuario) {
        try {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombres(usuario.getNombres().toUpperCase());
            nuevoUsuario.setApellidos(usuario.getApellidos().toUpperCase());
            nuevoUsuario.setCorreo(usuario.getCorreo().toLowerCase());
            nuevoUsuario.setTelefono(usuario.getTelefono());
            nuevoUsuario.setEstado(true);
            nuevoUsuario.setDeleteAt(null);
            nuevoUsuario.setRol(usuario.getRol());
            usuarioRepository.save(nuevoUsuario);
            return ResponseEntity.ok("Usuario agregado exitosamente" );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo realizar la peticion");
        }

    }

    @Override
    public ResponseEntity<String> actualizarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuarioActualizado = new Usuario();
            usuarioActualizado.setId(id);
            usuarioActualizado.setNombres(usuario.getNombres().toUpperCase());
            usuarioActualizado.setApellidos(usuario.getApellidos().toUpperCase());
            usuarioActualizado.setCorreo(usuario.getCorreo().toLowerCase());
            usuarioActualizado.setTelefono(usuario.getTelefono());
            usuarioActualizado.setEstado(true);
            usuarioActualizado.setDeleteAt(null);
            usuarioActualizado.setRol(usuario.getRol());
            usuarioRepository.save(usuarioActualizado);
            return ResponseEntity.ok("Usuario Actualizado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Usuario con el ID " + id + " no fue encontrado");
    }

    @Override
    public ResponseEntity<String> eliminarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuarioEliminado = new Usuario();
            usuarioEliminado.setId(id);
            usuarioEliminado.setNombres(usuarioOptional.get().getNombres());
            usuarioEliminado.setApellidos(usuarioOptional.get().getApellidos());
            usuarioEliminado.setCorreo(usuarioOptional.get().getCorreo());
            usuarioEliminado.setTelefono(usuarioOptional.get().getTelefono());
            usuarioEliminado.setEstado(false);
            usuarioEliminado.setDeleteAt(LocalDateTime.now());
            usuarioEliminado.setRol(usuarioOptional.get().getRol());
            usuarioRepository.save(usuarioEliminado);
            return ResponseEntity.ok("Usuario Eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Usuario con el ID " + id + " no fue encontrado");
    }
}
