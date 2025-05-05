package com.funny.autismo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funny.autismo_app.model.Crianca;
import com.funny.autismo_app.model.Responsavel;
import com.funny.autismo_app.model.Diagnostico;
import com.funny.autismo_app.model.Atividade;
import com.funny.autismo_app.repository.CriancaRepository;
import com.funny.autismo_app.repository.ResponsavelRepository;
import com.funny.autismo_app.repository.DiagnosticoRepository;
import com.funny.autismo_app.repository.AtividadeRepository;

@Service
public class CriancaService {

    @Autowired
    private CriancaRepository criancaRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

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
        // Associando Responsável, Diagnóstico e Atividades
        if (crianca.getResponsavel() != null && crianca.getResponsavel().getId() != null) {
            Responsavel responsavel = responsavelRepository.findById(crianca.getResponsavel().getId())
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
            crianca.setResponsavel(responsavel);
        }

        if (crianca.getDiagnostico() != null && crianca.getDiagnostico().getId() != null) {
            Diagnostico diagnostico = diagnosticoRepository.findById(crianca.getDiagnostico().getId())
                .orElseThrow(() -> new RuntimeException("Diagnóstico não encontrado"));
            crianca.setDiagnostico(diagnostico);
        }

        if (crianca.getAtividades() != null) {
            List<Atividade> atividades = crianca.getAtividades();
            for (Atividade atividade : atividades) {
                atividade = atividadeRepository.findById(atividade.getId())
                    .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
            }
            crianca.setAtividades(atividades);
        }

        return criancaRepository.save(crianca);
    }

    // Atualiza criança existente
    public Crianca atualizarCrianca(Long id, Crianca criancaAtualizada) {
        return criancaRepository.findById(id).map(crianca -> {
            crianca.setNome(criancaAtualizada.getNome());
            crianca.setIdade(criancaAtualizada.getIdade());

            // Atualizando o Responsável, Diagnóstico e Atividades
            if (criancaAtualizada.getResponsavel() != null && criancaAtualizada.getResponsavel().getId() != null) {
                Responsavel responsavel = responsavelRepository.findById(criancaAtualizada.getResponsavel().getId())
                    .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
                crianca.setResponsavel(responsavel);
            }

            if (criancaAtualizada.getDiagnostico() != null && criancaAtualizada.getDiagnostico().getId() != null) {
                Diagnostico diagnostico = diagnosticoRepository.findById(criancaAtualizada.getDiagnostico().getId())
                    .orElseThrow(() -> new RuntimeException("Diagnóstico não encontrado"));
                crianca.setDiagnostico(diagnostico);
            }

            if (criancaAtualizada.getAtividades() != null) {
                List<Atividade> atividades = criancaAtualizada.getAtividades();
                for (Atividade atividade : atividades) {
                    atividade = atividadeRepository.findById(atividade.getId())
                        .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
                }
                crianca.setAtividades(atividades);
            }

            return criancaRepository.save(crianca);
        }).orElseThrow(() -> new RuntimeException("Criança não encontrada com o id: " + id));
    }

    // Deleta criança pelo ID
    public void deletarCrianca(Long id) {
        criancaRepository.deleteById(id);
    }
}
