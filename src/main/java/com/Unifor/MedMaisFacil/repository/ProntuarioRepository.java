package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.ProntuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProntuarioRepository extends JpaRepository<ProntuarioEntity, Long> {
    Optional<ProntuarioEntity> findByAtendimentoId(Long atendimentoId);
    List<ProntuarioEntity> findByAtendimentoIdIn(List<Long> atendimentoIds);
}
