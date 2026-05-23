package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.hospital.HospitalMetricaResponseDTO;
import com.Unifor.MedMaisFacil.dtos.hospital.HospitalRequestDTO;
import com.Unifor.MedMaisFacil.dtos.hospital.HospitalResponseDTO;
import com.Unifor.MedMaisFacil.entity.HospitalEntity;
import com.Unifor.MedMaisFacil.models.Hospital;
import com.Unifor.MedMaisFacil.models.HospitalMetrica;
import org.springframework.stereotype.Component;

@Component
public class HospitalMapperImpl implements HospitalMapper {

    @Override
    public Hospital toModel(HospitalEntity entity) {
        return new Hospital(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getCnpj(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getStatusHospital(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm()
        );
    }

    @Override
    public HospitalEntity toEntity(Hospital model) {
        return new HospitalEntity(
                model.getId(),
                model.getNome(),
                model.getEndereco(),
                model.getCnpj(),
                model.getCidade(),
                model.getEstado(),
                model.getStatusHospital(),
                model.getCriadoEm(),
                model.getAtualizadoEm()
        );
    }

    @Override
    public Hospital toModel(HospitalRequestDTO dto) {
        return Hospital.builder()
                .nome(dto.nome())
                .endereco(dto.endereco())
                .cnpj(dto.cnpj())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .statusHospital(dto.statusHospital())
                .build();
    }

    @Override
    public HospitalResponseDTO toDTO(Hospital model) {
        return new HospitalResponseDTO(
                model.getId(),
                model.getNome(),
                model.getEndereco(),
                model.getCnpj(),
                model.getCidade(),
                model.getEstado(),
                model.getStatusHospital(),
                model.getCriadoEm(),
                model.getAtualizadoEm()
        );
    }

    @Override
    public HospitalMetricaResponseDTO toMetricasDTO(HospitalMetrica model) {
        return new HospitalMetricaResponseDTO(
                model.getTotalHospitais(),
                model.getAtivoHospitais(),
                model.getLotadoHospitais(),
                model.getEmManutencaoHospitais()
        );
    }
}
