package com.funny.autismo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funny.autismo_app.model.Atividade;
import com.funny.autismo_app.repository.AtividadeRepository;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    // Lista todas as atividades
    public List<Atividade> listarTodas() {
        return atividadeRepository.findAll();
    }

    // Busca atividade pelo ID
    public Optional<Atividade> buscarPorId(Long id) {
        return atividadeRepository.findById(id);
    }

    // Cria uma nova atividade
    public Atividade criarAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    // Atualiza atividade existente
    public Atividade atualizarAtividade(Long id, Atividade atividadeAtualizada) {
        return atividadeRepository.findById(id).map(atividade -> {
            atividade.setTitulo(atividadeAtualizada.getTitulo());
            atividade.setDescricao(atividadeAtualizada.getDescricao());
            atividade.setCategoria(atividadeAtualizada.getCategoria());
            atividade.setNivelDificuldade(atividadeAtualizada.getNivelDificuldade());
            return atividadeRepository.save(atividade);
        }).orElseThrow(() -> new RuntimeException("Atividade não encontrada com o id: " + id));
    }

    // Deleta atividade pelo ID
    public void deletarAtividade(Long id) {
        atividadeRepository.deleteById(id);
    }
}
