package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
