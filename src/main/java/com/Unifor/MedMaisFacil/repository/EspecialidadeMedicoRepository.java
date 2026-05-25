package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.EspecialidadeMedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadeMedicoRepository extends JpaRepository<EspecialidadeMedicoEntity, Long> {

    Optional<EspecialidadeMedicoEntity> findByNome(String nome);
}
