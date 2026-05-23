package com.Unifor.MedMaisFacil.dtos.hospital;

import com.Unifor.MedMaisFacil.enums.StatusHospital;

public record HospitalRequestDTO(
        String nome,
        String endereco,
        String cnpj,
        String cidade,
        String estado,
        StatusHospital statusHospital
) {}
