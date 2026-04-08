package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.chamado.ChamadoResponseDTO;
import com.Unifor.MedMaisFacil.dtos.chamadoSintoma.ChamadoSintomaResponseDTO;
import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Chamado toModel(ChamadoRequestDTO dto) {
        return Chamado.builder()
                .descricaoRelato(dto.descricaoRelato())
                .paciente(Paciente.builder().id(dto.pacienteId()).build())
                .hospital(Hospital.builder().id(dto.hospitalId()).build())
                .build();
    }

    @Override
    public ChamadoResponseDTO toDTO(Chamado model, List<ChamadoSintoma> chamadoSintomas) {
        return new ChamadoResponseDTO(
                model.getId(),
                model.getDescricaoRelato(),
                model.getStatusChamado(),
                model.getPrioridadeChamado(),
                model.getDataHoraChamado(),
                model.getCriadoEm(),
                model.getAtualizadoEm(),
                model.getPaciente().getId(),
                model.getPaciente().getNome(),
                model.getHospital().getId(),
                model.getHospital().getNome(),
                chamadoSintomas.stream()
                        .map(sintoma -> new ChamadoSintomaResponseDTO(
                                sintoma.getSintoma().getId(),
                                sintoma.getSintoma().getDescricao(),
                                sintoma.getIntensidade()
                        ))
                        .toList()
        );
    }

    @Override
    public List<SintomaDoChamado> toSintomas(ChamadoRequestDTO dto) {
        return dto.sintomas().stream()
                .map(sintoma -> new SintomaDoChamado(
                        sintoma.getId(),
                        sintoma.getIntensidade(),
                        sintoma.getDescricaoLivre()
                )).toList();
    }
}
