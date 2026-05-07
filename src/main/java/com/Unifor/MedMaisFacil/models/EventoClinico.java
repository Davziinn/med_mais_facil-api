package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.Severidade;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoClinico {

    private Long id;
    private String descricao;
    private Severidade severidade;

}
