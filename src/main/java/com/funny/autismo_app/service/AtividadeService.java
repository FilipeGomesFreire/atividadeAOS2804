package com.funny.autismo_app.service;

import com.funny.autismo_app.model.Atividade;
import com.funny.autismo_app.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    // Lista todas as atividades
    public List<Atividade> listarTodas() {
        return atividadeRepository.findAll();
    }

    // Busca uma atividade pelo ID
    public Optional<Atividade> buscarPorId(Long id) {
        return atividadeRepository.findById(id);
    }

    // Cria uma nova atividade
    public Atividade criarAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    // Atualiza uma atividade existente
    public Atividade atualizarAtividade(Long id, Atividade novaAtividade) {
        return atividadeRepository.findById(id).map(atividade -> {
            atividade.setTitulo(novaAtividade.getTitulo());
            atividade.setDescricao(novaAtividade.getDescricao());
            atividade.setCategoria(novaAtividade.getCategoria());
            atividade.setNivelDificuldade(novaAtividade.getNivelDificuldade());
            return atividadeRepository.save(atividade);
        }).orElseThrow(() -> new RuntimeException("Atividade não encontrada com id: " + id));
    }

    // Deleta uma atividade pelo ID
    public void deletarAtividade(Long id) {
        atividadeRepository.deleteById(id);
    }
}
