package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.exame.ExameRequestDTO;
import com.Unifor.MedMaisFacil.dtos.exame.ExameResponseDTO;
import com.Unifor.MedMaisFacil.entity.ExameEntity;
import com.Unifor.MedMaisFacil.models.Exame;
import org.springframework.stereotype.Component;

@Component
public class ExameMapperImpl implements ExameMapper {

    @Override
    public Exame toModel(ExameEntity entity) {
        return new Exame(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm()
        );
    }

    @Override
    public ExameEntity toEntity(Exame model) {
        return new ExameEntity(
                model.getId(),
                model.getNome(),
                model.getDescricao(),
                model.getCriadoEm(),
                model.getAtualizadoEm()
        );
    }

    @Override
    public Exame toModel(ExameRequestDTO dto) {
        return Exame.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .build();
    }

    @Override
    public ExameResponseDTO toDTO(Exame model) {
        return new ExameResponseDTO(
                model.getNome()
        );
    }
}
