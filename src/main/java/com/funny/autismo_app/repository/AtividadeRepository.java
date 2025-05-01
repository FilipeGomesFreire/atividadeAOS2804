package com.funny.autismo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.funny.autismo_app.model.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}
