package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.configuracao.ConfiguracaoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.configuracao.ConfiguracaoResponseDTO;
import com.Unifor.MedMaisFacil.entity.ConfiguracaoEntity;
import com.Unifor.MedMaisFacil.models.Configuracao;
import org.springframework.stereotype.Component;

@Component
public class ConfiguracaoMapperImpl implements ConfiguracaoMapper{


    @Override
    public Configuracao toModel(ConfiguracaoEntity entity) {
        return Configuracao.builder()
                .id(entity.getId())
                .tempoLimiteChamado(entity.getTempoLimiteChamado())
                .quantidadeMaximaFila(entity.getQuantidadeMaximaFila())
                .chamadaAutomatica(entity.getChamadaAutomatica())
                .statusGeral(entity.getStatusGeral())
                .mensagemPaciente(entity.getMensagemPaciente())
                .notificacoesPush(entity.getNotificacoesPush())
                .build();
    }

    @Override
    public ConfiguracaoEntity toEntity(Configuracao model) {
        return ConfiguracaoEntity.builder()
                .id(model.getId())
                .tempoLimiteChamado(model.getTempoLimiteChamado())
                .quantidadeMaximaFila(model.getQuantidadeMaximaFila())
                .chamadaAutomatica(model.getChamadaAutomatica())
                .statusGeral(model.getStatusGeral())
                .mensagemPaciente(model.getMensagemPaciente())
                .notificacoesPush(model.getNotificacoesPush())
                .build();
    }

    @Override
    public Configuracao toModel(ConfiguracaoRequestDTO dto) {
        return Configuracao.builder()
                .tempoLimiteChamado(dto.tempoLimiteChamado())
                .quantidadeMaximaFila(dto.quantidadeMaximaFila())
                .chamadaAutomatica(dto.chamadaAutomatica())
                .statusGeral(dto.statusGeral())
                .mensagemPaciente(dto.mensagemPaciente())
                .notificacoesPush(dto.notificacoesPush())
                .build();
    }

    @Override
    public ConfiguracaoResponseDTO toDTO(Configuracao model) {
        return new ConfiguracaoResponseDTO(
                model.getId(),
                model.getTempoLimiteChamado(),
                model.getQuantidadeMaximaFila(),
                model.getChamadaAutomatica(),
                model.getStatusGeral(),
                model.getMensagemPaciente(),
                model.getNotificacoesPush()
        );
    }
}
