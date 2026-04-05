package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.HospitalMedicoEntity;
import com.Unifor.MedMaisFacil.models.HospitalMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HospitalMedicoMapperImpl implements HospitalMedicoMapper {

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public HospitalMedico toModel(HospitalMedicoEntity entity) {
        return new HospitalMedico(
                entity.getId(),
                entity.getHospital() != null
                        ? hospitalMapper.toModel(entity.getHospital())
                        : null,
                entity.getMedico() != null
                        ? medicoMapper.toModel(entity.getMedico())
                        : null,
                entity.getCriadoEm()
        );
    }

    @Override
    public HospitalMedicoEntity toEntity(HospitalMedico model) {
        return new HospitalMedicoEntity(
                model.getId(),
                model.getHospital() != null
                        ? hospitalMapper.toEntity(model.getHospital())
                        : null,
                model.getMedico() != null
                        ? medicoMapper.toEntity(model.getMedico())
                        : null,
                model.getCriadoEm()
        );
    }
}
