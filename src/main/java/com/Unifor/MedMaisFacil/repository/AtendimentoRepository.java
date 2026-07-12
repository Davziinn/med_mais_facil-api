package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.AtendimentoEntity;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Query("""
        SELECT a FROM AtendimentoEntity a
        JOIN FETCH a.chamado c
        WHERE a.medico.id = :medicoId
          AND c.statusChamado IN :status
          AND a.dataInicio BETWEEN :inicio AND :fim
        ORDER BY a.dataInicio DESC
    """)
    List<AtendimentoEntity> buscarPorMedicoEStatusEPeriodo(
            @Param("medicoId") Long medicoId,
            @Param("status") List<StatusChamado> status,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

    long countByMedicoIdAndDataInicioBetween(
            Long medicoId, LocalDateTime inicio, LocalDateTime fim);

    @Query("""
        SELECT COUNT(a) FROM AtendimentoEntity a
        JOIN a.chamado c
        WHERE a.medico.id = :medicoId
          AND c.statusChamado = :status
          AND a.dataInicio BETWEEN :inicio AND :fim
    """)
    long countByMedicoIdAndChamadoStatusAndDataInicioBetween(
            @Param("medicoId") Long medicoId,
            @Param("status") StatusChamado status,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

    @Query("""
    SELECT a FROM AtendimentoEntity a
    WHERE a.medico.id = :medicoId
      AND a.chamado.statusChamado IN :status
      AND a.dataInicio BETWEEN :inicio AND :fim
    ORDER BY a.dataInicio DESC
""")
    List<AtendimentoEntity> findHistoricoDoMedico(
            @Param("medicoId") Long medicoId,
            @Param("status") List<StatusChamado> status,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

    // Lista de atendimentos finalizados no período
    List<AtendimentoEntity> findByDataFimBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query(
            value = """
        SELECT EXTRACT(DOW FROM dt_fim), COUNT(id_atend)
        FROM tb_atend
        WHERE dt_fim >= :inicio
          AND dt_fim <= :fim
        GROUP BY EXTRACT(DOW FROM dt_fim)
    """,
            nativeQuery = true
    )
    List<Object[]> contarAtendimentosPorDia(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

    Optional<AtendimentoEntity> findByChamadoId(Long chamadoId);

}
