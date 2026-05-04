package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.ChamadoEventoClinicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoEventoClinicoRepository extends JpaRepository<ChamadoEventoClinicoEntity, Long> {
    List<ChamadoEventoClinicoEntity> deleteByChamadoId(Long chamadoId);
}
