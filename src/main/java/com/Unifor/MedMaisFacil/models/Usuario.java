package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senhaHash;
    private String cpf;
    private TipoUsuario tipoUsuario;
    private Boolean ativo;
    private Hospital hospital;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}
