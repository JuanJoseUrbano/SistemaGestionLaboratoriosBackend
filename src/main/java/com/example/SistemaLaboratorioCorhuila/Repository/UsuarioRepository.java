package com.example.SistemaLaboratorioCorhuila.Repository;

import com.example.SistemaLaboratorioCorhuila.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByEstadoIsTrue();
    @Query("SELECT u FROM Usuario u WHERE u.nombres LIKE %:filtro% OR u.correo LIKE %:filtro% OR u.telefono LIKE %:filtro%")
    List<Usuario> buscarPorNombresCorreoTelefono(@Param("filtro") String filtro);
}
