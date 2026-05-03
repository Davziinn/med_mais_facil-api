package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.prescricao.PrescricaoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.prescricao.PrescricaoResponseDTO;
import com.Unifor.MedMaisFacil.entity.PrescricaoEntity;
import com.Unifor.MedMaisFacil.entity.PrescricaoMedicamentoEntity;
import com.Unifor.MedMaisFacil.models.Prescricao;
import com.Unifor.MedMaisFacil.models.PrescricaoMedicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrescricaoMapperImpl implements PrescricaoMapper {

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private PrescricaoMedicamentoMapper prescricaoMedicamentoMapper;

    @Override
    public Prescricao toModel(PrescricaoEntity entity) {
        return new Prescricao(
                entity.getId(),
                entity.getOrientacoes(),
                entity.getRetornoConsulta(),
                entity.getExames(),
                entity.getAtendimento() != null
                        ? atendimentoMapper.toModel(entity.getAtendimento())
                        : null,
                entity.getMedicamentos() != null
                        ? entity.getMedicamentos().stream()
                        .map(prescricaoMedicamentoMapper::toModel)
                        .toList()
                        :List.of()
        );
    }

    @Override
    public PrescricaoEntity toEntity(Prescricao model) {

        // 1. Monta a entity da prescrição primeiro
        PrescricaoEntity entity = new PrescricaoEntity();
        entity.setId(model.getId());
        entity.setOrientacoes(model.getOrientacoes());
        entity.setRetornoConsulta(model.getRetornoConsulta());
        entity.setExames(model.getExames());
        entity.setAtendimento(
                model.getAtendimento() != null
                        ? atendimentoMapper.toEntity(model.getAtendimento())
                        : null
        );

        if (model.getMedicamentos() != null) {
            List<PrescricaoMedicamentoEntity> medicamentos = new ArrayList<>();

            for (PrescricaoMedicamento med : model.getMedicamentos()) {
                PrescricaoMedicamentoEntity medEntity = prescricaoMedicamentoMapper.toEntity(med);
                medEntity.setPrescricao(entity);
                medicamentos.add(medEntity);
            }

            entity.setMedicamentos(medicamentos);
        }

        return entity;
    }

    @Override
    public Prescricao toModel(PrescricaoRequestDTO dto) {
        return Prescricao.builder()
                .orientacoes(dto.orientacoes())
                .retornoConsulta(dto.retornoConsulta())
                .exames(dto.exames())
                .medicamentos(dto.medicamentos() != null ? dto.medicamentos().stream().map(prescricaoMedicamentoMapper::toModel).toList() : List.of())
                .build();
    }

    @Override
    public PrescricaoResponseDTO toDTO(Prescricao model) {
        return new PrescricaoResponseDTO(
                model.getId(),
                model.getOrientacoes(),
                model.getRetornoConsulta(),
                model.getExames(),
                model.getAtendimento() != null ? atendimentoMapper.toSalvarDTO(model.getAtendimento()) : null,
                model.getMedicamentos() != null
                        ? model.getMedicamentos().stream()
                        .map(prescricaoMedicamentoMapper::toDTO)
                        .toList()
                        : List.of()
        );
    }
}
