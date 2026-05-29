package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    @Query("SELECT p FROM PacienteEntity p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<PacienteEntity> findByNomeContaining(@Param("nome") String nome);

    @Query("SELECT COUNT(p) FROM PacienteEntity p")
    long countPacientes();
}
