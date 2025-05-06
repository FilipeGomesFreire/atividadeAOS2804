package com.funny.autismo_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.funny.autismo_app.model.Crianca;
import com.funny.autismo_app.model.Diagnostico;
import com.funny.autismo_app.model.Responsavel;
import com.funny.autismo_app.service.CriancaService;

@RestController
@CrossOrigin(origins = "*")
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

    // POST /criancas com responsavelId e diagnosticoId
    @PostMapping
    public ResponseEntity<?> criarCrianca(@RequestBody Map<String, Object> dados) {
        try {
            Crianca crianca = new Crianca();
            crianca.setNome((String) dados.get("nome"));
            crianca.setIdade((int) dados.get("idade"));

            if (dados.containsKey("responsavelId")) {
                Responsavel resp = new Responsavel();
                resp.setId(Long.parseLong(dados.get("responsavelId").toString()));
                crianca.setResponsavel(resp);
            }

            if (dados.containsKey("diagnosticoId")) {
                Diagnostico diag = new Diagnostico();
                diag.setId(Long.parseLong(dados.get("diagnosticoId").toString()));
                crianca.setDiagnostico(diag);
            }

            Crianca salva = criancaService.criarCrianca(crianca);
            return ResponseEntity.ok(salva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar criança: " + e.getMessage());
        }
    }

    // PUT /criancas/{id} com responsavelId e diagnosticoId
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCrianca(@PathVariable Long id, @RequestBody Map<String, Object> dados) {
        try {
            Crianca crianca = new Crianca();
            crianca.setNome((String) dados.get("nome"));
            crianca.setIdade((int) dados.get("idade"));

            if (dados.containsKey("responsavelId")) {
                Responsavel resp = new Responsavel();
                resp.setId(Long.parseLong(dados.get("responsavelId").toString()));
                crianca.setResponsavel(resp);
            }

            if (dados.containsKey("diagnosticoId")) {
                Diagnostico diag = new Diagnostico();
                diag.setId(Long.parseLong(dados.get("diagnosticoId").toString()));
                crianca.setDiagnostico(diag);
            }

            Crianca atualizada = criancaService.atualizarCrianca(id, crianca);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar criança: " + e.getMessage());
        }
    }

    // DELETE /criancas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCrianca(@PathVariable Long id) {
        criancaService.deletarCrianca(id);
        return ResponseEntity.noContent().build();
    }
}
