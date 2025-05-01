package com.funny.autismo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funny.autismo_app.model.Diagnostico;
import com.funny.autismo_app.repository.DiagnosticoRepository;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    // Lista todos os diagnósticos
    public List<Diagnostico> listarTodos() {
        return diagnosticoRepository.findAll();
    }

    // Busca diagnóstico pelo ID
    public Optional<Diagnostico> buscarPorId(Long id) {
        return diagnosticoRepository.findById(id);
    }

    // Cria um novo diagnóstico
    public Diagnostico criarDiagnostico(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    // Atualiza diagnóstico existente
    public Diagnostico atualizarDiagnostico(Long id, Diagnostico diagnosticoAtualizado) {
        return diagnosticoRepository.findById(id).map(diagnostico -> {
            diagnostico.setTipo(diagnosticoAtualizado.getTipo());
            return diagnosticoRepository.save(diagnostico);
        }).orElseThrow(() -> new RuntimeException("Diagnóstico não encontrado com o id: " + id));
    }

    // Deleta diagnóstico pelo ID
    public void deletarDiagnostico(Long id) {
        diagnosticoRepository.deleteById(id);
    }
}
