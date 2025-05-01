package com.funny.autismo_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.funny.autismo_app.model.Crianca;
import com.funny.autismo_app.service.CriancaService;

@RestController
@RequestMapping("/criancas")
public class CriancaController {

    @Autowired
    private CriancaService criancaService;

    // GET /criancas
    @GetMapping
    public List<Crianca> listarTodas() {
        return criancaService.listarTodas();
    }

    // GET /criancas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Crianca> buscarPorId(@PathVariable Long id) {
        return criancaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /criancas
    @PostMapping
    public Crianca criarCrianca(@RequestBody Crianca crianca) {
        return criancaService.criarCrianca(crianca);
    }

    // PUT /criancas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Crianca> atualizarCrianca(@PathVariable Long id, @RequestBody Crianca crianca) {
        try {
            Crianca atualizada = criancaService.atualizarCrianca(id, crianca);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /criancas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCrianca(@PathVariable Long id) {
        criancaService.deletarCrianca(id);
        return ResponseEntity.noContent().build();
    }
}
