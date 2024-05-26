package com.example.SistemaLaboratorioCorhuila.InterfaceService;

import com.example.SistemaLaboratorioCorhuila.Entity.TipoLaboratorio;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceTipoLaboratorio {
    List<TipoLaboratorio> obtenerTiposLaboratorios();
    Optional<TipoLaboratorio> obtenerPorId(Long id);
    ResponseEntity<String> registrarTipoLaboratorio(TipoLaboratorio tipoLaboratorio);
    ResponseEntity<String> actualizarTipoLaboratorio(Long id, TipoLaboratorio tipoLaboratorio);
    ResponseEntity<String> eliminarTipoLaboratorio(Long id);
}
