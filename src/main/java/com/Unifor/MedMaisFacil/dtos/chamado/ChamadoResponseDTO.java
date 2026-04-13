package com.Unifor.MedMaisFacil.dtos.chamado;

import com.Unifor.MedMaisFacil.dtos.sintomaChamado.SintomaChamadoResponseDTO;
import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.enums.StatusChamado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ChamadoResponseDTO (
        Long id,
        String descricaoRelato,
        StatusChamado statusChamado,
        PrioridadeChamado prioridadeChamado,
        LocalDateTime dataHoraChamado,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,

        Long pacienteId,
        String nomePaciente,
        String cpf,
        LocalDate dataNascimento,
        String sexo,

        Long hospitalId,
        String nomeHospital,

        List<SintomaChamadoResponseDTO> chamadoSintomas
){}
