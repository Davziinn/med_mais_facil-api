package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.Severidade;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SinaisAlerta {

    private String descricao;
    private Severidade severidade;
}
