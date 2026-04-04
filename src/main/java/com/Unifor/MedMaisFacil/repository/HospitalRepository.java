package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    @Query("SELECT c FROM ChamadoEntity c WHERE c.hospital.id = :hospitalId " +
            "AND c.statusChamado = 'EM_ESPERA'")
    List<ChamadoEntity> findFilaByHospital(@Param("hospitalId") Long hospitalId);
}
