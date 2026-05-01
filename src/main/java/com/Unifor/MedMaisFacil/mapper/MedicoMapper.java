package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.medico.MedicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.medico.MedicoResponseDTO;
import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import com.Unifor.MedMaisFacil.models.Medico;

public interface MedicoMapper {

    Medico toModel (MedicoEntity entity);

    MedicoEntity toEntity (Medico model);

    Medico toModel (MedicoRequestDTO dto);

    MedicoResponseDTO toDTO (Medico model);
}
