package com.funny.autismo_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.funny.autismo_app.model.Atividade;
import com.funny.autismo_app.service.AtividadeService;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    // GET /atividades
    @GetMapping
    public List<Atividade> listarTodas() {
        return atividadeService.listarTodas();
    }

    // GET /atividades/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Atividade> buscarPorId(@PathVariable Long id) {
        return atividadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /atividades
    @PostMapping
    public Atividade criarAtividade(@RequestBody Atividade atividade) {
        return atividadeService.criarAtividade(atividade);
    }

    // PUT /atividades/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Atividade> atualizarAtividade(@PathVariable Long id, @RequestBody Atividade atividade) {
        try {
            Atividade atualizada = atividadeService.atualizarAtividade(id, atividade);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /atividades/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtividade(@PathVariable Long id) {
        atividadeService.deletarAtividade(id);
        return ResponseEntity.noContent().build();
    }
}
