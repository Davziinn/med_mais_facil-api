package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.GuiaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuiaMedicaRepository extends JpaRepository<GuiaMedicaEntity, Long> {

    Optional<List<GuiaMedicaEntity>> findAllByAtendimentoId (Long atendimentoId);
}
