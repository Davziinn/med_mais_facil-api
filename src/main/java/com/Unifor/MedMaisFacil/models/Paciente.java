package com.Unifor.MedMaisFacil.models;

import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Paciente {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}
