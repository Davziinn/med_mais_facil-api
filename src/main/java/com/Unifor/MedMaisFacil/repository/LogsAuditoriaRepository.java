package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.LogsAuditoriaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsAuditoriaRepository extends JpaRepository<LogsAuditoriaEntity, Long> {
    Page<LogsAuditoriaEntity> findAllByOrderByCriadoEmDesc (Pageable pageable);

    Page<LogsAuditoriaEntity> findByUsuarioIdOrderByCriadoEmDesc (Long usuarioId, Pageable pageable);

    Page<LogsAuditoriaEntity> findByModuloOrderByCriadoEmDesc (String modulo, Pageable pageable);
}
