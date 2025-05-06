package com.funny.autismo_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.funny.autismo_app.model.Diagnostico;
import com.funny.autismo_app.service.DiagnosticoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    // GET /diagnosticos
    @GetMapping
    public List<Diagnostico> listarTodos() {
        return diagnosticoService.listarTodos();
    }

    // GET /diagnosticos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> buscarPorId(@PathVariable Long id) {
        return diagnosticoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /diagnosticos (corrigido)
    @PostMapping
    public ResponseEntity<?> criarDiagnostico(@RequestBody Map<String, Object> dados) {
        try {
            Diagnostico diagnostico = new Diagnostico();
            diagnostico.setTipo((String) dados.get("tipo"));  // campo correto

            Diagnostico salvo = diagnosticoService.criarDiagnostico(diagnostico);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar diagnóstico: " + e.getMessage());
        }
    }

    // PUT /diagnosticos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Diagnostico> atualizarDiagnostico(@PathVariable Long id, @RequestBody Diagnostico diagnostico) {
        try {
            Diagnostico atualizado = diagnosticoService.atualizarDiagnostico(id, diagnostico);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /diagnosticos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiagnostico(@PathVariable Long id) {
        diagnosticoService.deletarDiagnostico(id);
        return ResponseEntity.noContent().build();
    }
}
