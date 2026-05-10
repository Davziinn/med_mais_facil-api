package com.Unifor.MedMaisFacil.dtos.fila.filaEmAtendimento;

import com.Unifor.MedMaisFacil.enums.StatusChamado;

public record FilaEmAtendimentoResponseDTO (
        Long chamadoId,
        String nomePaciente,
        String descricaoRelato,
        Integer idadePaciente,
        StatusChamado statusChamado
) {}
