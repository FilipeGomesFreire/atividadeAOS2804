package com.funny.autismo_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.funny.autismo_app.model.Atividade;
import com.funny.autismo_app.model.Crianca;
import com.funny.autismo_app.model.ProgressoAtividade;
import com.funny.autismo_app.service.ProgressoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/progresso")
public class ProgressoController {
    
    @Autowired
    private ProgressoService progressoService;

    // POST corrigido para evitar erro 415
    @PostMapping("/registrar")
public ResponseEntity<?> registrarProgresso(@RequestBody Map<String, Object> dados) {
    try {
        ProgressoAtividade progresso = new ProgressoAtividade();

        Long criancaId = Long.parseLong(dados.get("criancaId").toString());
        Long atividadeId = Long.parseLong(dados.get("atividadeId").toString());
        int pontuacao = Integer.parseInt(dados.get("pontuacao").toString());
        String observacoes = (String) dados.getOrDefault("observacoes", "");
        boolean concluida = Boolean.parseBoolean(dados.get("concluida").toString());

        Crianca crianca = new Crianca();
        crianca.setId(criancaId);
        progresso.setCrianca(crianca);

        Atividade atividade = new Atividade();
        atividade.setId(atividadeId);
        progresso.setAtividade(atividade);

        progresso.setPontuacao(pontuacao);
        progresso.setObservacoes(observacoes);
        progresso.setConcluida(concluida);
        progresso.setData(java.time.LocalDate.now());

        return ResponseEntity.ok(progressoService.registrarProgresso(progresso));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Erro ao registrar progresso: " + e.getMessage());
    }
}


    // GET progresso por criança
    @GetMapping("/crianca/{id}")
    public List<ProgressoAtividade> buscarProgressoPorCrianca(@PathVariable Long id) {
        return progressoService.buscarProgressoPorCrianca(id);
    }

    // GET progresso por atividade
    @GetMapping("/atividade/{id}")
    public List<ProgressoAtividade> buscarProgressoPorAtividade(@PathVariable Long id) {
        return progressoService.buscarProgressoPorAtividade(id);
    }

    // GET resumo de progresso
    @GetMapping("/crianca/{id}/resumo")
    public Map<String, Object> gerarResumoProgresso(@PathVariable Long id) {
        return progressoService.gerarResumoProgresso(id);
    }
}
