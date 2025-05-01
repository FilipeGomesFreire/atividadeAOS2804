package com.funny.autismo_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.funny.autismo_app.model.ProgressoAtividade;
import com.funny.autismo_app.service.ProgressoService;

@RestController
@RequestMapping("/progresso")
public class ProgressoController {
    
    @Autowired
    private ProgressoService progressoService;
    
    @PostMapping("/registrar")
    public ProgressoAtividade registrarProgresso(@RequestBody ProgressoAtividade progresso) {
        return progressoService.registrarProgresso(progresso);
    }
    
    @GetMapping("/crianca/{id}")
    public List<ProgressoAtividade> buscarProgressoPorCrianca(@PathVariable Long id) {
        return progressoService.buscarProgressoPorCrianca(id);
    }
    
    @GetMapping("/atividade/{id}")
    public List<ProgressoAtividade> buscarProgressoPorAtividade(@PathVariable Long id) {
        return progressoService.buscarProgressoPorAtividade(id);
    }
    
    @GetMapping("/crianca/{id}/resumo")
    public Map<String, Object> gerarResumoProgresso(@PathVariable Long id) {
        return progressoService.gerarResumoProgresso(id);
    }
} 