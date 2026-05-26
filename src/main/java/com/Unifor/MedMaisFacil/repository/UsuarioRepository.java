package com.Unifor.MedMaisFacil.repository;

import com.Unifor.MedMaisFacil.entity.UsuarioEntity;
import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    List<UsuarioEntity> findByTipoUsuarioNot (TipoUsuario tipoUsuario);
}
