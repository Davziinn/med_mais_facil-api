package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoResponseDTO;
import com.Unifor.MedMaisFacil.entity.EspecialidadeMedicoEntity;
import com.Unifor.MedMaisFacil.models.EspecialidadeMedico;

public interface EspecialidadeMapper {

    EspecialidadeMedico toModel (EspecialidadeMedicoEntity entity);

    EspecialidadeMedicoEntity toEntity (EspecialidadeMedico model);

    EspecialidadeMedico toModel (EspecialidadeMedicoRequestDTO dto);

    EspecialidadeMedicoResponseDTO toDTO (EspecialidadeMedico model);
}
