package com.Unifor.MedMaisFacil.dtos.usuario;

import com.Unifor.MedMaisFacil.enums.TipoUsuario;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        String cpf,
        String telefone,
        TipoUsuario tipoUsuario,
        Long hospitalId
) {
}
