package com.Unifor.MedMaisFacil.dtos.usuario;

import com.Unifor.MedMaisFacil.dtos.hospital.HospitalResponseDTO;
import com.Unifor.MedMaisFacil.enums.TipoUsuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        TipoUsuario tipoUsuario,
        Boolean ativo,
        HospitalResponseDTO hospital
) {
}
