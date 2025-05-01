package com.funny.autismo_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.funny.autismo_app.model.Responsavel;
import com.funny.autismo_app.service.ResponsavelService;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    // GET /responsaveis
    @GetMapping
    public List<Responsavel> listarTodos() {
        return responsavelService.listarTodos();
    }

    // GET /responsaveis/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> buscarPorId(@PathVariable Long id) {
        return responsavelService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /responsaveis
    @PostMapping
    public Responsavel criarResponsavel(@RequestBody Responsavel responsavel) {
        return responsavelService.criarResponsavel(responsavel);
    }

    // PUT /responsaveis/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> atualizarResponsavel(@PathVariable Long id, @RequestBody Responsavel responsavel) {
        try {
            Responsavel atualizado = responsavelService.atualizarResponsavel(id, responsavel);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /responsaveis/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarResponsavel(@PathVariable Long id) {
        responsavelService.deletarResponsavel(id);
        return ResponseEntity.noContent().build();
    }
}
