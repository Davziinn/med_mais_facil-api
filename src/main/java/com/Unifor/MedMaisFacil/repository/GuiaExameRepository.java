package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.GuiaExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuiaExameRepository extends JpaRepository<GuiaExameEntity, Long> {
    @Modifying
    @Query("DELETE FROM GuiaExameEntity ge WHERE ge.guia.id = :guiaId")
    void deleteAllByGuiaId(@Param("guiaId") Long guiaId);
}
