package com.Unifor.MedMaisFacil.models;

import java.util.List;

public record ChamadoAberto (
        Chamado chamado,
        List<ChamadoSintoma> chamadoSintomas
){
}
