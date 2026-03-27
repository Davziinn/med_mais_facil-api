package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.AgendamentoExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepositoy extends JpaRepository<AgendamentoExameEntity, Long> {
}
