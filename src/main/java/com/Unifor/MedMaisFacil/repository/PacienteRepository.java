package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
}
