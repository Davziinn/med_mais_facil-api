package com.Unifor.MedMaisFacil.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Exame {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}
