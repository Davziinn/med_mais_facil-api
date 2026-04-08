package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.hospital.HospitalRequestDTO;
import com.Unifor.MedMaisFacil.dtos.hospital.HospitalResponseDTO;
import com.Unifor.MedMaisFacil.entity.HospitalEntity;
import com.Unifor.MedMaisFacil.models.Hospital;

public interface HospitalMapper {

    Hospital toModel (HospitalEntity entity);

    HospitalEntity toEntity (Hospital model);

    Hospital toModel (HospitalRequestDTO dto);

    HospitalResponseDTO toDTO (Hospital model);
}
