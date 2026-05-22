package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.especialidade.EspecialidadeMedicoResponseDTO;
import com.Unifor.MedMaisFacil.entity.EspecialidadeMedicoEntity;
import com.Unifor.MedMaisFacil.models.EspecialidadeMedico;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadeMapperImpl implements EspecialidadeMapper{
    @Override
    public EspecialidadeMedico toModel(EspecialidadeMedicoEntity entity) {
        return new EspecialidadeMedico(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );
    }

    @Override
    public EspecialidadeMedicoEntity toEntity(EspecialidadeMedico model) {
        return new EspecialidadeMedicoEntity(
                model.getId(),
                model.getNome(),
                model.getDescricao()
        );
    }

    @Override
    public EspecialidadeMedico toModel(EspecialidadeMedicoRequestDTO dto) {
        return EspecialidadeMedico.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .build();
    }

    @Override
    public EspecialidadeMedicoResponseDTO toDTO(EspecialidadeMedico model) {
        return new EspecialidadeMedicoResponseDTO(
                model.getId(),
                model.getNome(),
                model.getDescricao()
        );
    }
}
