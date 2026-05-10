package com.Unifor.MedMaisFacil.models;

public record SintomaDoChamado(
        Long sintomaId,
        Integer intensidade,
        String tempoSintoma,
        String frequencia
) {}
