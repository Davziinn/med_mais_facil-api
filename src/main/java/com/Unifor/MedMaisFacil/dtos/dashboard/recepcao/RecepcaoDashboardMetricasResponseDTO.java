package com.Unifor.MedMaisFacil.dtos.dashboard.recepcao;

import java.time.LocalDateTime;

public record RecepcaoDashboardMetricasResponseDTO (
        long aguardandoCheckin,
        long aguardando,
        long ausentes,
        long aguardandoEncaminhamento,
        LocalDateTime tempoMedioEspera
) {}
