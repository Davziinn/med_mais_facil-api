package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.HospitalMedicoEntity;
import com.Unifor.MedMaisFacil.models.HospitalMedico;

public interface HospitalMedicoMapper {

    HospitalMedico toModel (HospitalMedicoEntity entity);

    HospitalMedicoEntity toEntity (HospitalMedico model);
}
