package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.prescricaoMedicamento.*;
import com.Unifor.MedMaisFacil.entity.PrescricaoMedicamentoEntity;
import com.Unifor.MedMaisFacil.models.PrescricaoMedicamento;

public interface PrescricaoMedicamentoMapper {

    PrescricaoMedicamento toModel (PrescricaoMedicamentoEntity entity);

    PrescricaoMedicamentoEntity toEntity (PrescricaoMedicamento model);

    PrescricaoMedicamento toModel (PrescricaoMedicamentoRequestDTO dto);

    PrescricaoMedicamentoResponseDTO toDTO (PrescricaoMedicamento model);
}
