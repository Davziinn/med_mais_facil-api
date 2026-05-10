package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.chamadoSintoma.ChamadoSintomaResponseDTO;
import com.Unifor.MedMaisFacil.entity.*;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChamadoSintomaMapperImpl implements ChamadoSintomaMapper {

    @Autowired
    private SintomaMapper sintomaMapper;

    @Autowired
    private ChamadoMapper chamadoMapper;

    @Override
    public ChamadoSintoma toModel(ChamadoSintomaEntity entity) {
        return new ChamadoSintoma(
                entity.getId(),
                entity.getIntensidade(),
                entity.getDescricaoLivre(),
                entity.getDataRegistro(),
                entity.getTempoSintoma(),
                entity.getFrequencia(),
                entity.getChamado() != null
                        ? chamadoMapper.toModel(entity.getChamado())
                        : null,
                entity.getSintoma() != null
                        ? sintomaMapper.toModel(entity.getSintoma())
                        : null
        );
    }

    @Override
    public ChamadoSintomaEntity toEntity(ChamadoSintoma model) {
        ChamadoEntity referenciaDoChamado = ChamadoEntity.builder()
                .id(model.getChamado().getId())
                .build();

        return ChamadoSintomaEntity.builder()
                .id(model.getId())
                .intensidade(model.getIntensidade())
                .descricaoLivre(model.getDescricaoLivre())
                .tempoSintoma(model.getTempoSintoma())
                .frequencia(model.getFrequencia())
                .dataRegistro(model.getDataRegistro())
                .chamado(referenciaDoChamado)
                .sintoma(model.getSintoma() != null
                        ? sintomaMapper.toEntity(model.getSintoma())
                        : null
                )
                .build();
    }

    @Override
    public ChamadoSintoma toModel(ChamadoSintomaResponseDTO dto) {
        return new ChamadoSintoma(
                dto.id(),
                dto.intensidade(),
                dto.descricaoLivre(),
                dto.dataRegistro(),
                dto.tempoSintoma(),
                dto.frequencia(),
                dto.chamadoResponseDTO() != null
                        ? chamadoMapper.toModel(dto.chamadoResponseDTO())
                        : null,
                dto.sintomaResponseDTO() != null
                        ? sintomaMapper.toModel(dto.sintomaResponseDTO())
                        : null
        );
    }

    @Override
    public ChamadoSintomaResponseDTO toDTO(ChamadoSintoma model) {
        return new ChamadoSintomaResponseDTO(
                model.getId(),
                model.getIntensidade(),
                model.getDescricaoLivre(),
                model.getTempoSintoma(),
                model.getFrequencia(),
                model.getDataRegistro(),
                model.getChamado() != null
                        ? chamadoMapper.toDTO(model.getChamado(), List.of())
                        : null,
                model.getSintoma() != null
                        ? sintomaMapper.toDTO(model.getSintoma())
                        : null
        );
    }

    @Override
    public List<ChamadoSintoma> toModelList(List<ChamadoSintomaEntity> entities) {
        return entities.stream()
                .map(this::toModel)
                .toList();
    }
}
