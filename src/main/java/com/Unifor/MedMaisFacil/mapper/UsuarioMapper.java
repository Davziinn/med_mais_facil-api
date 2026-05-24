package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioRequestDTO;
import com.Unifor.MedMaisFacil.dtos.usuario.UsuarioResponseDTO;
import com.Unifor.MedMaisFacil.entity.UsuarioEntity;
import com.Unifor.MedMaisFacil.models.Usuario;

public interface UsuarioMapper {
    Usuario toModel (UsuarioEntity entity);

    UsuarioEntity toEntity (Usuario model);

    Usuario toModel (UsuarioRequestDTO dto);

    UsuarioResponseDTO toDTO (Usuario model);
}
