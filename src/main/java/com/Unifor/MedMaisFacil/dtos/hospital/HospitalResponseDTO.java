package com.Unifor.MedMaisFacil.dtos.hospital;

import com.Unifor.MedMaisFacil.enums.StatusHospital;

import java.time.LocalDateTime;

public record HospitalResponseDTO (
        Long id,
        String nome,
        String endereco,
        String cnpj,
        String cidade,
        String estado,
        StatusHospital statusHospital,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
