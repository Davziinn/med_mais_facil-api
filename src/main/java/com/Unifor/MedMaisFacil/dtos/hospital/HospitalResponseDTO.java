package com.Unifor.MedMaisFacil.dtos.hospital;

import java.time.LocalDateTime;

public record HospitalResponseDTO (
        Long id,
        String nome,
        String endereco,
        String cnpj,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
