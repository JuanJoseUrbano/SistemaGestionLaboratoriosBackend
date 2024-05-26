package com.example.SistemaLaboratorioCorhuila.Controller;

import com.example.SistemaLaboratorioCorhuila.Entity.Laboratorio;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceLaboratorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/laboratorio-corhuila/laboratorio")
public class LaboratorioController {
    @Autowired
    IServiceLaboratorio iServiceLaboratorio;

    @GetMapping("/encontrar-todos")
    public List<Laboratorio> obtener() {
        return iServiceLaboratorio.obtenerLaboratorios();
    }

    @GetMapping("/busqueda")
    public List<Laboratorio> busqueda(@Param("filtro") String filtro) {
        return iServiceLaboratorio.obtenerPorBusqueda(filtro);
    }

    @GetMapping("/encontrar-por/{id}")
    public Optional<Laboratorio> obtenerPorId(@PathVariable Long id) {
        return iServiceLaboratorio.obtenerPorId(id);
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody(required = true) Laboratorio laboratorio) {
        return iServiceLaboratorio.registrarLaboratorio(laboratorio);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Laboratorio laboratorio) {
        return iServiceLaboratorio.actualizarLaboratorio(id, laboratorio);
    }

    @PutMapping("/eliminar-por/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable Long id) {
        return iServiceLaboratorio.eliminarLaboratorio(id);
    }
}
