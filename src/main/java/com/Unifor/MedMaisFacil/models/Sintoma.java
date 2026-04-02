package com.Unifor.MedMaisFacil.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Sintoma {

    private Long id;
    private String descricao;
}
