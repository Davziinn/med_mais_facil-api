package com.Unifor.MedMaisFacil.dtos.usuario;

import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UsuarioRequestDTO(

        @NotNull(message = "O campo [NOME] não pode ser null")
        String nome,

        @Email(message = "O campo [EMAIL] está inválido")
        String email,

        @Length(message = "O campo [SENHA] deve conter no mínimo 6 caracteres válidos", min = 6)
        String senha,

        @NotBlank(message = "O campo [CPF] é obrigatório")
        @Length(message = "O campo [CPF] deve conter 14 caracteres")
        String cpf,
        String telefone,
        TipoUsuario tipoUsuario,
        Long hospitalId,
        Boolean ativo
) {
}
