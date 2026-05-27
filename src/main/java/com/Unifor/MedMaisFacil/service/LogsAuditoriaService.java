package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.dtos.logsAuditoria.LogsAuditoriaResponseDTO;
import com.Unifor.MedMaisFacil.entity.LogsAuditoriaEntity;
import com.Unifor.MedMaisFacil.mapper.LogsAuditoriaMapper;
import com.Unifor.MedMaisFacil.models.LogsAuditoria;
import com.Unifor.MedMaisFacil.repository.LogsAuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LogsAuditoriaService {

    private final LogsAuditoriaRepository logsAuditoriaRepository;

    private final LogsAuditoriaMapper logsAuditoriaMapper;

    public void registrarLog (LogsAuditoria logs) {
        logsAuditoriaRepository.save(logsAuditoriaMapper.toEntity(logs));
    }

    public Page<LogsAuditoriaResponseDTO> listarLogs(LogsAuditoria filtro, Pageable page) {
        Page<LogsAuditoriaEntity> entidades;

        if (Objects.nonNull(filtro.getUsuarioId())) {
            entidades = logsAuditoriaRepository.findByUsuarioIdOrderByCriadoEmDesc(filtro.getUsuarioId(), page);
        } else if (Objects.nonNull(filtro.getModulo())) {
            entidades = logsAuditoriaRepository.findByModuloOrderByCriadoEmDesc(filtro.getModulo(), page);
        } else {
            entidades = logsAuditoriaRepository.findAllByOrderByCriadoEmDesc(page);
        }

        return entidades.map(entity -> logsAuditoriaMapper.toDTO(logsAuditoriaMapper.toModel(entity)));
    }
}
