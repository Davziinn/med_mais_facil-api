package com.Unifor.MedMaisFacil.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class HospitalMetrica {
    private long totalHospitais;
    private long ativoHospitais;
    private long lotadoHospitais;
    private long emManutencaoHospitais;
}
