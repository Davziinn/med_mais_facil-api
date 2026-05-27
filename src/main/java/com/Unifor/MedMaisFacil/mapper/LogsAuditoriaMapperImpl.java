package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.logsAuditoria.LogsAuditoriaResponseDTO;
import com.Unifor.MedMaisFacil.entity.LogsAuditoriaEntity;
import com.Unifor.MedMaisFacil.models.LogsAuditoria;
import org.springframework.stereotype.Component;

@Component
public class LogsAuditoriaMapperImpl implements LogsAuditoriaMapper {
    @Override
    public LogsAuditoria toModel(LogsAuditoriaEntity entity) {
        return LogsAuditoria.builder()
                .id(entity.getId())
                .usuarioId(entity.getUsuarioId())
                .nomeUsuario(entity.getNomeUsuario())
                .acao(entity.getAcao())
                .modulo(entity.getModulo())
                .detalhes(entity.getDetalhes())
                .criadoEm(entity.getCriadoEm())
                .build();
    }

    @Override
    public LogsAuditoriaEntity toEntity(LogsAuditoria model) {
        return LogsAuditoriaEntity.builder()
                .id(model.getId())
                .usuarioId(model.getUsuarioId())
                .nomeUsuario(model.getNomeUsuario())
                .acao(model.getAcao())
                .modulo(model.getModulo())
                .detalhes(model.getDetalhes())
                .criadoEm(model.getCriadoEm())
                .build();
    }

    @Override
    public LogsAuditoriaResponseDTO toDTO(LogsAuditoria model) {
        return new LogsAuditoriaResponseDTO(
                model.getId(),
                model.getUsuarioId(),
                model.getNomeUsuario(),
                model.getAcao(),
                model.getModulo(),
                model.getDetalhes(),
                model.getCriadoEm()
        );
    }
}
