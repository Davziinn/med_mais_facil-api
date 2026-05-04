package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ChamadoEventoClinicoEntity;
import com.Unifor.MedMaisFacil.models.ChamadoEventoClinico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChamadoEventoClinicoMapperImpl implements ChamadoEventoClinicoMapper {

    private final ChamadoMapper chamadoMapper;
    private final EventoClinicoMapper eventoClinicoMapper;

    @Override
    public ChamadoEventoClinico toModel(ChamadoEventoClinicoEntity entity) {
        return new ChamadoEventoClinico(
                entity.getId(),
                entity.getChamado() != null
                        ? chamadoMapper.toModel(entity.getChamado())
                        : null,
                entity.getEventoClinico() != null
                        ? eventoClinicoMapper.toModel(entity.getEventoClinico())
                        : null,
                entity.getDataRegistro()
        );
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
}
