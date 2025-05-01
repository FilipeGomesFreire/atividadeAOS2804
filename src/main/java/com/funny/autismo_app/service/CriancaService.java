package com.funny.autismo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funny.autismo_app.model.Crianca;
import com.funny.autismo_app.repository.CriancaRepository;

@Service
public class CriancaService {

    @Autowired
    private CriancaRepository criancaRepository;

    // Lista todas as crianças
    public List<Crianca> listarTodas() {
        return criancaRepository.findAll();
    }

    // Busca criança pelo ID
    public Optional<Crianca> buscarPorId(Long id) {
        return criancaRepository.findById(id);
    }

    // Cria uma nova criança
    public Crianca criarCrianca(Crianca crianca) {
        return criancaRepository.save(crianca);
    }

    // Atualiza criança existente
    public Crianca atualizarCrianca(Long id, Crianca criancaAtualizada) {
        return criancaRepository.findById(id).map(crianca -> {
            crianca.setNome(criancaAtualizada.getNome());
            crianca.setIdade(criancaAtualizada.getIdade());
            crianca.setResponsavel(criancaAtualizada.getResponsavel());
            crianca.setDiagnostico(criancaAtualizada.getDiagnostico());
            crianca.setAtividades(criancaAtualizada.getAtividades());
            return criancaRepository.save(crianca);
        }).orElseThrow(() -> new RuntimeException("Criança não encontrada com o id: " + id));
    }

    // Deleta criança pelo ID
    public void deletarCrianca(Long id) {
        criancaRepository.deleteById(id);
    }
}
