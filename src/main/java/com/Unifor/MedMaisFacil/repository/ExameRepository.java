package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.ExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExameRepository extends JpaRepository<ExameEntity, Long> {

    List<ExameEntity> findAllByAtivo (boolean ativo);
}
