package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.logsAuditoria.LogsAuditoriaResponseDTO;
import com.Unifor.MedMaisFacil.entity.LogsAuditoriaEntity;
import com.Unifor.MedMaisFacil.models.LogsAuditoria;

public interface LogsAuditoriaMapper {

    LogsAuditoria toModel (LogsAuditoriaEntity entity);

    LogsAuditoriaEntity toEntity (LogsAuditoria model);

    LogsAuditoriaResponseDTO toDTO (LogsAuditoria model);
}
