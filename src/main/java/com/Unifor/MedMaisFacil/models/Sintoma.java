package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Sintoma {

    private Long id;
    private String descricao;
    private Integer intensidade;
    private PrioridadeChamado prioridadeSintoma;
    private Boolean ativo;
}
