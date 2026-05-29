package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.HospitalEntity;
import com.Unifor.MedMaisFacil.enums.StatusHospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    long countByStatusHospital(StatusHospital status);

    @Query("""
        SELECT COUNT (h) FROM HospitalEntity h
    """)
    long countHospitais();
}
