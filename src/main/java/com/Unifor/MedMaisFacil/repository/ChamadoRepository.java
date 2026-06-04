package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long> {
    @Query("""
            SELECT DISTINCT c
            FROM ChamadoEntity c
            LEFT JOIN FETCH c.chamadoSintomas cs
            WHERE c.statusChamado NOT IN :status
                AND c.dataHoraChamado BETWEEN :dataInicio AND :dataFim
        """)
    List<ChamadoEntity> buscarChamadosAtivosComSintomasAndDataHoraChamadoBetween(@Param("status") List<StatusChamado> status, @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);


    long countByStatusChamadoAndDataHoraChamadoBetween(StatusChamado status, LocalDateTime inicio, LocalDateTime fim);

    long countByPrioridadeChamadoAndDataHoraChamadoBetween(PrioridadeChamado nivelPrioridade, LocalDateTime inicio, LocalDateTime fim);

    long countByCriadoEmBetween(LocalDateTime inicio, LocalDateTime fim);

    List<ChamadoEntity> findByStatusChamadoAndDataHoraChamadoBetweenOrderByDataHoraChamadoAsc(StatusChamado status, LocalDateTime inicio, LocalDateTime fim);

    @Query("""
            SELECT c FROM ChamadoEntity c
            WHERE c.hospital.id = :hospitalId
            AND c.statusChamado IN :status
        """)
    List<ChamadoEntity> findFilaByHospital(
            @Param("hospitalId") Long hospitalId,
            @Param("status") List<StatusChamado> status
    );
}
