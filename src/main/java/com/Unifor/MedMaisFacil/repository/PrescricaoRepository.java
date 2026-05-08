package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.PrescricaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrescricaoRepository extends JpaRepository<PrescricaoEntity, Long> {

    List<PrescricaoEntity> findByAtendimentoIdIn(List<Long> atendimentoIds);
    Optional<PrescricaoEntity> findByAtendimentoId(Long atendimentoId);
}
