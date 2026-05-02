package com.Unifor.MedMaisFacil.dtos.salvarAtendimento;


public record SalvarAtendimentoRequestDTO(
        String anamnese,
        String exameFisico,
        String hipoteseDiagnostica,
        String cidDoenca,
        String conduta
) {
}
