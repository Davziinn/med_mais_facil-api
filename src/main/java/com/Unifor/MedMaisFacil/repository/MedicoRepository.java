package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
}
