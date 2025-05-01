package com.funny.autismo_app.repository;

import com.funny.autismo_app.model.Crianca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriancaRepository extends JpaRepository<Crianca, Long> {
}
