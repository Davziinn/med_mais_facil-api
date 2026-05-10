package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, Long> {

    boolean existsByChamadoId (Long chamadoId);

    @Query(
            """
                SELECT a
                FROM AtendimentoEntity a
                JOIN FETCH a.chamado c
                JOIN FETCH c.paciente
                JOIN FETCH a.medico
                WHERE a.dataFim IS NOT NULL
                AND c.statusChamado IN ('FINALIZADO', 'CANCELADO')
            """
    )
    List<AtendimentoEntity> findByHistoricoAtendimento ();

    @Query("""
            SELECT a FROM AtendimentoEntity a
            JOIN FETCH a.chamado c
            JOIN FETCH c.paciente p
            JOIN FETCH a.medico m
            WHERE p.id = :pacienteId
            ORDER BY a.dataInicio DESC
        """
    )
    List<AtendimentoEntity> findByPacienteId(@Param("pacienteId") Long pacienteId);

    // Total de atendimentos no período
    long countByDataInicioBetween(LocalDateTime inicio, LocalDateTime fim );

    // Atendimentos finalizados no período
    long countByDataFimBetween(LocalDateTime inicio, LocalDateTime fim);

    // Lista de atendimentos finalizados no período
    List<AtendimentoEntity> findByDataFimBetween(LocalDateTime inicio, LocalDateTime fim);
}
