package com.funny.autismo_app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funny.autismo_app.model.ProgressoAtividade;
import com.funny.autismo_app.repository.ProgressoRepository;

@Service
public class ProgressoService {
    
    @Autowired
    private ProgressoRepository progressoRepository;
    
    public ProgressoAtividade registrarProgresso(ProgressoAtividade progresso) {
        return progressoRepository.save(progresso);
    }
    
    public List<ProgressoAtividade> buscarProgressoPorCrianca(Long criancaId) {
        return progressoRepository.findByCriancaId(criancaId);
    }
    
    public List<ProgressoAtividade> buscarProgressoPorAtividade(Long atividadeId) {
        return progressoRepository.findByAtividadeId(atividadeId);
    }
    
    public Map<String, Object> gerarResumoProgresso(Long criancaId) {
        List<ProgressoAtividade> progressos = buscarProgressoPorCrianca(criancaId);
        
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalAtividades", progressos.size());
        resumo.put("mediaPontuacao", calcularMediaPontuacao(progressos));
        resumo.put("atividadesConcluidas", contarAtividadesConcluidas(progressos));
        resumo.put("evolucaoPorCategoria", calcularEvolucaoPorCategoria(progressos));
        
        return resumo;
    }
    
    private double calcularMediaPontuacao(List<ProgressoAtividade> progressos) {
        if (progressos.isEmpty()) return 0.0;
        return progressos.stream()
                .mapToInt(ProgressoAtividade::getPontuacao)
                .average()
                .orElse(0.0);
    }
    
    private long contarAtividadesConcluidas(List<ProgressoAtividade> progressos) {
        return progressos.stream()
                .filter(ProgressoAtividade::isConcluida)
                .count();
    }
    
    private Map<String, Double> calcularEvolucaoPorCategoria(List<ProgressoAtividade> progressos) {
        return progressos.stream()
                .collect(Collectors.groupingBy(
                    p -> p.getAtividade().getCategoria(),
                    Collectors.averagingInt(ProgressoAtividade::getPontuacao)
                ));
    }
} 