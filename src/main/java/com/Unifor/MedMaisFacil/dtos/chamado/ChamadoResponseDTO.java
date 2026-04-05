package com.Unifor.MedMaisFacil.dtos.chamado;

import com.Unifor.MedMaisFacil.dtos.sintoma.SintomaResponseDTO;
import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.enums.StatusChamado;

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

        Long hospitalId,
        String nomeHospital,

        List<SintomaResponseDTO> chamadoSintomas
){}
