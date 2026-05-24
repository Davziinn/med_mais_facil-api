package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Medico {

    private Long id;
    private String crm;
    private EspecialidadeMedico especialidade;
    private String sexo;
    private LocalDate dataNascimento;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private Usuario usuario;
}
