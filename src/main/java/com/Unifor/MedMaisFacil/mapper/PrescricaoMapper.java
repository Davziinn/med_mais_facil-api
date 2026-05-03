package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.prescricao.PrescricaoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.prescricao.PrescricaoResponseDTO;
import com.Unifor.MedMaisFacil.entity.PrescricaoEntity;
import com.Unifor.MedMaisFacil.models.Prescricao;

public interface PrescricaoMapper {

    Prescricao toModel (PrescricaoEntity entity);

    PrescricaoEntity toEntity (Prescricao model);

    Prescricao toModel (PrescricaoRequestDTO dto);

    PrescricaoResponseDTO toDTO (Prescricao model);
}
