package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.ChamadoSintomaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoSintomaRepository extends JpaRepository<ChamadoSintomaEntity, Long> {
    List<ChamadoSintomaEntity> findByChamadoId(Long chamadoId);
}
