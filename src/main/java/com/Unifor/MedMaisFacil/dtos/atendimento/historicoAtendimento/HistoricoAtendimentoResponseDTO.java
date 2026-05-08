package com.Unifor.MedMaisFacil.dtos.atendimento.historicoAtendimento;

import java.time.LocalDateTime;

public record HistoricoAtendimentoResponseDTO(

        Long atendimentoId,
        Long pacienteId,
//        String senha,
        String nomePaciente,
        Integer idadePaciente,
        String sexoPaciente,

        String diagnostico,

        String nomeMedico,

        String prioridade,

        String status,

        LocalDateTime dataInicio,
        LocalDateTime dataFim
//      Long tempoAtendimento

) {
}
