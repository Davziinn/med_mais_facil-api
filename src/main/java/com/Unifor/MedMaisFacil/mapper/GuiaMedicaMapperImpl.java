package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaCanceladaResponseDTO;
import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaRequestDTO;
import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaResponseDTO;
import com.Unifor.MedMaisFacil.entity.GuiaMedicaEntity;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.Exame;
import com.Unifor.MedMaisFacil.models.GuiaMedica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuiaMedicaMapperImpl implements GuiaMedicaMapper {

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private ExameMapper exameMapper;

    @Override
    public GuiaMedica toModel(GuiaMedicaEntity entity) {
        return GuiaMedica.builder()
                .id(entity.getId())
                .numeroGuia(entity.getNumeroGuia())
                .dataSolicitacao(entity.getDataSolicitacao())
                .convenio(entity.getConvenio())
                .cidExame(entity.getCidExame())
                .indicacaoClinica(entity.getIndicacaoClinica())
                .statusGuiaMedica(entity.getStatusGuiaMedica())
                .observacoes(entity.getObservacoes())
                .atendimento(entity.getAtendimento() != null
                        ? atendimentoMapper.toModel(entity.getAtendimento())
                        : null
                )
                .exames(entity.getExames() != null
                        ? entity.getExames().stream().map(guiaExame -> exameMapper.toModel(guiaExame.getExame())).toList()
                        : null
                )
                .build();
    }

    @Override
    public GuiaMedicaEntity toEntity(GuiaMedica model) {
        return GuiaMedicaEntity.builder()
                .id(model.getId())
                .numeroGuia(model.getNumeroGuia())
                .dataSolicitacao(model.getDataSolicitacao())
                .statusGuiaMedica(model.getStatusGuiaMedica())
                .convenio(model.getConvenio())
                .cidExame(model.getCidExame())
                .indicacaoClinica(model.getIndicacaoClinica())
                .observacoes(model.getObservacoes())
                .atendimento(model.getAtendimento() != null
                        ? atendimentoMapper.toEntity(model.getAtendimento())
                        : null
                )
                .build();
    }

    @Override
    public GuiaMedica toModel(GuiaMedicaRequestDTO dto) {
        return GuiaMedica.builder()
                .convenio(dto.convenio())
                .cidExame(dto.cidExame())
                .indicacaoClinica(dto.indicacaoClinica())
                .observacoes(dto.observacoes())
                .atendimento(dto.atendimentoId() != null ? Atendimento.builder().id(dto.atendimentoId()).build() : null)
                .exames(dto.examesIds() != null ? dto.examesIds().stream().map(exameId -> Exame.builder().id(exameId).build()).toList() : null)
                .build();
    }

    @Override
    public GuiaMedicaResponseDTO toDTO(GuiaMedica model) {
        return new GuiaMedicaResponseDTO(
                model.getId(),
                model.getCidExame(),
                model.getIndicacaoClinica(),
                model.getNumeroGuia(),
                model.getDataSolicitacao(),
                model.getConvenio(),
                model.getObservacoes(),
                model.getAtendimento().getId(),
                model.getExames() != null ? model.getExames().stream().map(exameMapper::toDTO).toList() : null
        );
    }

    @Override
    public GuiaMedicaCanceladaResponseDTO toDTOCancelada(GuiaMedica model) {
        return new GuiaMedicaCanceladaResponseDTO(
                model.getStatusGuiaMedica()
        );
    }
}
