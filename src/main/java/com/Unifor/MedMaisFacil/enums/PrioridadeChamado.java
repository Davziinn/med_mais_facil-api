package com.Unifor.MedMaisFacil.enums;

public enum PrioridadeChamado {
    BAIXA(4),
    MEDIA(3),
    ALTA(2),
    CRITICA(1);

    private final int ordem;

    PrioridadeChamado (int ordem) {
        this.ordem = ordem;
    }

    public int getOrdem() {
        return ordem;
    }
}
