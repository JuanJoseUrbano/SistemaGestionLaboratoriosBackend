package com.example.SistemaLaboratorioCorhuila.Service;

import com.example.SistemaLaboratorioCorhuila.Entity.Laboratorio;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceLaboratorio;
import com.example.SistemaLaboratorioCorhuila.Repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService implements IServiceLaboratorio {
    @Autowired
    LaboratorioRepository laboratorioRepository;
    @Override
    public List<Laboratorio> obtenerLaboratorios() {
        return laboratorioRepository.findByEstadoIsTrue();
    }

    @Override
    public List<Laboratorio> obtenerPorBusqueda(String filtro) {
        return laboratorioRepository.findByNombreOrUbicacion(filtro.toUpperCase());
    }

    @Override
    public Optional<Laboratorio> obtenerPorId(Long id) {
        return laboratorioRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> registrarLaboratorio(Laboratorio laboratorio) {
        Laboratorio laboratorioNuevo = new Laboratorio();
        laboratorioNuevo.setNombre(laboratorio.getNombre().toUpperCase());
        laboratorioNuevo.setUbicacion(laboratorio.getUbicacion().toUpperCase());
        laboratorioNuevo.setCapacidad(laboratorio.getCapacidad());
        laboratorioNuevo.setEstado(true);
        laboratorioNuevo.setDeleteAt(null);
        laboratorioNuevo.setTipoLaboratorio(laboratorio.getTipoLaboratorio());
        laboratorioRepository.save(laboratorioNuevo);
        return ResponseEntity.ok("Laboratorio guardado correctamente");
    }

    @Override
    public ResponseEntity<String> actualizarLaboratorio(Long id, Laboratorio laboratorio) {
        Optional<Laboratorio> laboratorioBuscado = laboratorioRepository.findById(id);
        if(laboratorioBuscado.isPresent()){
            Laboratorio laboratorioActualizado = new Laboratorio();
            laboratorioActualizado.setId(id);
            laboratorioActualizado.setNombre(laboratorio.getNombre().toUpperCase());
            laboratorioActualizado.setCapacidad(laboratorio.getCapacidad());
            laboratorioActualizado.setUbicacion(laboratorio.getUbicacion().toUpperCase());
            laboratorioActualizado.setEstado(true);
            laboratorioActualizado.setDeleteAt(null);
            laboratorioActualizado.setTipoLaboratorio(laboratorio.getTipoLaboratorio());
            laboratorioRepository.save(laboratorioActualizado);
            return ResponseEntity.ok("Laboratorio Actualizado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Laboratorio con el ID " + id + " no fue encontrada");
    }

    @Override
    public ResponseEntity<String> eliminarLaboratorio(Long id) {
        Optional<Laboratorio> laboratorioBuscado = laboratorioRepository.findById(id);
        if(laboratorioBuscado.isPresent()){
            Laboratorio laboratorioEliminado = new Laboratorio();
            laboratorioEliminado.setId(id);
            laboratorioEliminado.setNombre(laboratorioBuscado.get().getNombre().toUpperCase());
            laboratorioEliminado.setCapacidad(laboratorioBuscado.get().getCapacidad());
            laboratorioEliminado.setUbicacion(laboratorioBuscado.get().getUbicacion());
            laboratorioEliminado.setEstado(false);
            laboratorioEliminado.setDeleteAt(LocalDateTime.now());
            laboratorioEliminado.setTipoLaboratorio(laboratorioBuscado.get().getTipoLaboratorio());
            laboratorioRepository.save(laboratorioEliminado);
            return ResponseEntity.ok("Laboratorio Eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Laboratorio con el ID " + id + " no fue encontrada");
    }
}
