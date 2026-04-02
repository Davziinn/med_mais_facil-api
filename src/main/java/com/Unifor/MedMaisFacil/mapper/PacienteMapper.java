package com.Unifor.MedMaisFacil.mapper;


import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.models.Paciente;

public interface PacienteMapper {
    Paciente toModel (PacienteEntity entity);

    PacienteEntity toEntity (Paciente model);
}
