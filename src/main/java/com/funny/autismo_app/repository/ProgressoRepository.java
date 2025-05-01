package com.funny.autismo_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.funny.autismo_app.model.ProgressoAtividade;

@Repository
public interface ProgressoRepository extends JpaRepository<ProgressoAtividade, Long> {
    List<ProgressoAtividade> findByCriancaId(Long criancaId);
    List<ProgressoAtividade> findByAtividadeId(Long atividadeId);
    List<ProgressoAtividade> findByCriancaIdAndConcluidaTrue(Long criancaId);
} 