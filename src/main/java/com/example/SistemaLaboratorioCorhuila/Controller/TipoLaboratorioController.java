package com.example.SistemaLaboratorioCorhuila.Controller;

import com.example.SistemaLaboratorioCorhuila.Entity.TipoLaboratorio;
import com.example.SistemaLaboratorioCorhuila.InterfaceService.IServiceTipoLaboratorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/laboratorio-corhuila/tipolaboratorio")
public class TipoLaboratorioController {
    @Autowired
    IServiceTipoLaboratorio iServiceTipoLaboratorio;
    @GetMapping("/encontrar-todos")
    public List<TipoLaboratorio> obtener() {
        return iServiceTipoLaboratorio.obtenerTiposLaboratorios();
    }
    @GetMapping("/encontrar-por/{id}")
    public Optional<TipoLaboratorio> obtenerPorId(@PathVariable Long id) {
        return iServiceTipoLaboratorio.obtenerPorId(id);
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody(required = true) TipoLaboratorio tipoLaboratorio) {
        return iServiceTipoLaboratorio.registrarTipoLaboratorio(tipoLaboratorio);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody TipoLaboratorio tipoLaboratorio) {
        return iServiceTipoLaboratorio.actualizarTipoLaboratorio(id, tipoLaboratorio);
    }

    @PutMapping("/eliminar-por/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable Long id) {
        return iServiceTipoLaboratorio.eliminarTipoLaboratorio(id);
    }
}
