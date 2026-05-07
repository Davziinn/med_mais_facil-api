package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ChamadoEventoClinicoEntity;
import com.Unifor.MedMaisFacil.entity.EventoClinicoEntity;
import com.Unifor.MedMaisFacil.models.ChamadoEventoClinico;
import com.Unifor.MedMaisFacil.models.EventoClinico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChamadoEventoClinicoMapperImpl implements ChamadoEventoClinicoMapper {

    private final ChamadoMapper chamadoMapper;
    private final EventoClinicoMapper eventoClinicoMapper;

    @Override
    public ChamadoEventoClinico toModel(ChamadoEventoClinicoEntity entity) {
        return ChamadoEventoClinico.builder()
                .id(entity.getId())
                .chamado(entity.getChamado() != null ? chamadoMapper.toModel(entity.getChamado()) : null)
                .eventoClinico(entity.getEventoClinico() != null ? eventoClinicoMapper.toModel(entity.getEventoClinico()) : null)
                .dataRegistro(entity.getDataRegistro())
                .build();
    }

    @Override
    public ChamadoEventoClinicoEntity toEntity(ChamadoEventoClinico model) {
        return new ChamadoEventoClinicoEntity(
                model.getId(),
                model.getChamado() != null
                        ? chamadoMapper.toEntity(model.getChamado())
                        : null,
                model.getEventoClinico() != null
                        ? eventoClinicoMapper.toEntity(model.getEventoClinico())
                        : null,
                model.getDataRegistro()
        );
    }

    @Override
    public List<ChamadoEventoClinico> toModelList(List<ChamadoEventoClinicoEntity> entities) {
        return entities.stream()
                .map(this::toModel)
                .toList();
    }
}
