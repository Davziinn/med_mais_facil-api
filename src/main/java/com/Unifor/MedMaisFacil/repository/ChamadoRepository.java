package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long> {
    @Query("""
    SELECT DISTINCT c
    FROM ChamadoEntity c
    LEFT JOIN FETCH c.chamadoSintomas cs
    WHERE c.statusChamado NOT IN :status
""")
    List<ChamadoEntity> buscarChamadosAtivosComSintomas(@Param("status") List<StatusChamado> status);
}
