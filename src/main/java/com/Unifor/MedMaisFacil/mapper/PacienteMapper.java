package com.Unifor.MedMaisFacil.mapper;


import com.Unifor.MedMaisFacil.dtos.paciente.PacienteRequestDTO;
import com.Unifor.MedMaisFacil.dtos.paciente.PacienteResponseDTO;
import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.models.Paciente;

public interface PacienteMapper {
    Paciente toModel (PacienteEntity entity);

    PacienteEntity toEntity (Paciente model);

    Paciente toModel (PacienteRequestDTO dto);

    PacienteResponseDTO toDTO (Paciente model);
}
