package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.PrescricaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrescricaoRepository extends JpaRepository<PrescricaoEntity, Long> {

    Optional<PrescricaoEntity> findByAtendimentoId(Long atendimentoId);
}
