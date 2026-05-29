package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    @Query("""
       SELECT COUNT(m)
       FROM MedicoEntity m
       WHERE m.usuario.hospital.id = :hospitalId
       """)
    long countMedicoByHospital(@Param("hospitalId") Long hospitalId);

    @Query("""
       SELECT COUNT(m)
       FROM MedicoEntity m
       WHERE m.usuario.ativo = true
       """)
    long countMedicosAtivos();
}
