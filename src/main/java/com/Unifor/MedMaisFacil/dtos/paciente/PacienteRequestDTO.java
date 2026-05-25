package com.Unifor.MedMaisFacil.dtos.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record PacienteRequestDTO (
        @NotNull(message = "O campo [NOME] não pode ser null")
        String nome,

        @NotBlank(message = "O campo [CPF] é obrigatório")
        @Length(message = "O campo [CPF] deve conter 14 caracteres")
        String cpf,

        String telefone,

        @Email(message = "O campo [EMAIL] está inválido")
        String email,

        @Length(message = "O campo [SENHA] deve conter no mínimo 6 caracteres válidos", min = 6)
        String senha,
        
        @NotNull(message = "O campo [DATA DE NASCIMENTO] é obrigatório")
        LocalDate dataNascimento,
        String sexo,

        List<String> condicoesPreexistentes
){}
