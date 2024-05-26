package com.example.SistemaLaboratorioCorhuila.Service;

import com.example.SistemaLaboratorioCorhuila.Entity.TipoLaboratorio;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceTipoLaboratorio;
import com.example.SistemaLaboratorioCorhuila.Repository.TipoLaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class TipoLaboratorioService implements IServiceTipoLaboratorio {
    @Autowired
    TipoLaboratorioRepository laboratorioRepository;
    @Override
    public List<TipoLaboratorio> obtenerTiposLaboratorios() {
        return laboratorioRepository.findByEstadoIsTrue();
    }

    @Override
    public Optional<TipoLaboratorio> obtenerPorId(Long id) {
        return laboratorioRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> registrarTipoLaboratorio(TipoLaboratorio tipoLaboratorio) {
        try{
        TipoLaboratorio tipoLabNuevo = new TipoLaboratorio();
        tipoLabNuevo.setNombre(tipoLaboratorio.getNombre().toUpperCase());
        tipoLabNuevo.setDescripcion(tipoLaboratorio.getDescripcion().toUpperCase());
        tipoLabNuevo.setEstado(true);
        tipoLabNuevo.setDeleteAt(null);
        laboratorioRepository.save(tipoLabNuevo);
        return ResponseEntity.ok("Tipo de laboratorio agregado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo realizar la peticion");
        }
    }

    @Override
    public ResponseEntity<String> actualizarTipoLaboratorio(Long id, TipoLaboratorio tipoLaboratorio) {
        Optional<TipoLaboratorio> tipoLaboratorioBuscado = laboratorioRepository.findById(id);
        if(tipoLaboratorioBuscado.isPresent()){
            TipoLaboratorio tipoLaboratorioActualizado = new TipoLaboratorio();
            tipoLaboratorioActualizado.setId(id);
            tipoLaboratorioActualizado.setNombre(tipoLaboratorio.getNombre());
            tipoLaboratorioActualizado.setDescripcion(tipoLaboratorio.getDescripcion());
            tipoLaboratorioActualizado.setEstado(true);
            tipoLaboratorioActualizado.setDeleteAt(null);
            laboratorioRepository.save(tipoLaboratorioActualizado);
            return ResponseEntity.ok("La Tipo de laboratorio fue Actualizado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Tipo de laboratorio con el ID " + id + " no fue encontrada");
    }

    @Override
    public ResponseEntity<String> eliminarTipoLaboratorio(Long id) {
        Optional<TipoLaboratorio> tipoLaboratorioBuscado = laboratorioRepository.findById(id);
        if(tipoLaboratorioBuscado.isPresent()){
            TipoLaboratorio tipoLaboratorioEliminado = new TipoLaboratorio();
            tipoLaboratorioEliminado.setId(id);
            tipoLaboratorioEliminado.setNombre(tipoLaboratorioBuscado.get().getNombre());
            tipoLaboratorioEliminado.setDescripcion(tipoLaboratorioBuscado.get().getDescripcion());
            tipoLaboratorioEliminado.setEstado(false);
            tipoLaboratorioEliminado.setDeleteAt(LocalDateTime.now());
            laboratorioRepository.save(tipoLaboratorioEliminado);
            return ResponseEntity.ok("La Tipo de laboratorio fue Eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Tipo de laboratorio con el ID " + id + " no fue encontrada");
    }
}
