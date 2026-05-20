package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.sintoma.SintomaRequestDTO;
import com.Unifor.MedMaisFacil.dtos.sintoma.SintomaResponseDTO;
import com.Unifor.MedMaisFacil.entity.SintomaEntity;
import com.Unifor.MedMaisFacil.models.Sintoma;
import org.springframework.stereotype.Component;

@Component
public class SintomaMapperImpl implements SintomaMapper{

    @Override
    public Sintoma toModel(SintomaEntity entity) {
        return Sintoma.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .prioridadeSintoma(entity.getPrioridadeSintoma())
                .ativo(entity.isAtivo())
                .build();
    }

    @Override
    public SintomaEntity toEntity(Sintoma model) {
        return new SintomaEntity(
                model.getId(),
                model.getDescricao(),
                model.getPrioridadeSintoma(),
                model.isAtivo()
        );
    }

    @Override
    public Sintoma toModel(SintomaRequestDTO dto) {
        return Sintoma.builder()
                .descricao(dto.descricao())
                .prioridadeSintoma(dto.prioridadeSintoma())
                .ativo(dto.ativo())
                .build();
    }


    @Override
    public SintomaResponseDTO toDTO(Sintoma model) {
        return new SintomaResponseDTO(
                model.getId(),
                model.getDescricao(),
                model.getPrioridadeSintoma(),
                model.isAtivo()
                );
    }

    @Override
    public Sintoma toModel(SintomaResponseDTO dto) {
        return Sintoma.builder()
                .id(dto.id())
                .descricao(dto.descricao())
                .prioridadeSintoma(dto.prioridadeSintoma())
                .ativo(dto.ativo())
                .build();
    }
}
