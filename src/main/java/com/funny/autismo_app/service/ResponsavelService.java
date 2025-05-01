package com.funny.autismo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funny.autismo_app.model.Responsavel;
import com.funny.autismo_app.repository.ResponsavelRepository;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    // Lista todos os responsáveis
    public List<Responsavel> listarTodos() {
        return responsavelRepository.findAll();
    }

    // Busca responsável pelo ID
    public Optional<Responsavel> buscarPorId(Long id) {
        return responsavelRepository.findById(id);
    }

    // Cria um novo responsável
    public Responsavel criarResponsavel(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    // Atualiza responsável existente
    public Responsavel atualizarResponsavel(Long id, Responsavel responsavelAtualizado) {
        return responsavelRepository.findById(id).map(responsavel -> {
            responsavel.setNome(responsavelAtualizado.getNome());
            responsavel.setEmail(responsavelAtualizado.getEmail());
            responsavel.setTelefone(responsavelAtualizado.getTelefone());
            return responsavelRepository.save(responsavel);
        }).orElseThrow(() -> new RuntimeException("Responsável não encontrado com o id: " + id));
    }

    // Deleta responsável pelo ID
    public void deletarResponsavel(Long id) {
        responsavelRepository.deleteById(id);
    }
}
