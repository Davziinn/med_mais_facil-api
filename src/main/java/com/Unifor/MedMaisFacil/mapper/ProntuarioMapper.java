package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ProntuarioEntity;
import com.Unifor.MedMaisFacil.models.Prontuario;

public interface ProntuarioMapper {

    Prontuario toModel (ProntuarioEntity entity);

    ProntuarioEntity toEntity (Prontuario model);
}
