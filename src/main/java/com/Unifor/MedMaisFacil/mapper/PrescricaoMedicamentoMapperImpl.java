package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.prescricaoMedicamento.PrescricaoMedicamentoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.prescricaoMedicamento.PrescricaoMedicamentoResponseDTO;
import com.Unifor.MedMaisFacil.entity.PrescricaoMedicamentoEntity;
import com.Unifor.MedMaisFacil.models.PrescricaoMedicamento;
import org.springframework.stereotype.Component;

@Component
public class PrescricaoMedicamentoMapperImpl implements PrescricaoMedicamentoMapper {

    @Override
    public PrescricaoMedicamento toModel(PrescricaoMedicamentoEntity entity) {
        return new PrescricaoMedicamento(
                entity.getId(),
                entity.getNome(),
                entity.getDose(),
                entity.getFrequencia(),
                entity.getDuracao(),
                entity.getVia()
//                entity.getPrescricao()
        );
    }

    @Override
    public PrescricaoMedicamentoEntity toEntity(PrescricaoMedicamento model) {
        return new PrescricaoMedicamentoEntity(
                model.getId(),
                model.getNome(),
                model.getDose(),
                model.getFrequencia(),
                model.getDuracao(),
                model.getVia(),
                null
        );
    }

    @Override
    public PrescricaoMedicamento toModel(PrescricaoMedicamentoRequestDTO dto) {
        return PrescricaoMedicamento.builder()
                .nome(dto.nome())
                .dose(dto.dose())
                .frequencia(dto.frequencia())
                .duracao(dto.duracao())
                .via(dto.via())
                .build();
    }

    @Override
    public PrescricaoMedicamentoResponseDTO toDTO(PrescricaoMedicamento model) {
        return new PrescricaoMedicamentoResponseDTO(
                model.getId(),
                model.getNome(),
                model.getDose(),
                model.getFrequencia(),
                model.getDuracao(),
                model.getVia()
        );
    }
}
