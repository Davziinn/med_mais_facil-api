package com.Unifor.MedMaisFacil.dtos.hospital;

public record HospitalMetricaResponseDTO(
        long totalHospitais,
        long ativoHospitais,
        long lotadoHospitais,
        long emManutencaoHospitais
) {
}
