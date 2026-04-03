package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.models.Chamado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChamadoMapperImpl implements ChamadoMapper {

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public Chamado toModel(ChamadoEntity entity) {
        return new Chamado(
                entity.getId(),
                entity.getDescricaoRelato(),
                entity.getStatusChamado(),
                entity.getPrioridadeChamado(),
                entity.getDataHoraChamado(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm(),
                entity.getPaciente() != null
                        ? pacienteMapper.toModel(entity.getPaciente())
                        : null,
                entity.getHospital() != null
                        ? hospitalMapper.toModel(entity.getHospital())
                        : null
        );
    }

    @Override
    public ChamadoEntity toEntity(Chamado model) {
        return new ChamadoEntity(
                model.getId(),
                model.getDescricaoRelato(),
                model.getStatusChamado(),
                model.getPrioridadeChamado(),
                model.getDataHoraChamado(),
                model.getCriadoEm(),
                model.getAtualizadoEm(),
                model.getPaciente() != null
                        ? pacienteMapper.toEntity(model.getPaciente())
                        : null,
                model.getHospital() != null
                        ? hospitalMapper.toEntity(model.getHospital())
                        : null
        );
    }
}
