package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;

import java.util.List;

public record ChamadoAberto (
        Chamado chamado,
        List<ChamadoSintoma> chamadoSintomas,
        PrioridadeChamado prioridadeChamado,
        String senha
){
}
