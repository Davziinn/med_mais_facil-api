package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.StatusAgendamentoExame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AgendamentoExame {

    private Long id;
    private LocalDateTime dataHoraAgendamento;
    private StatusAgendamentoExame status;
    private String observacoes;
    private Long guiaExameId;

    public AgendamentoExame (Long id, LocalDateTime dataHoraAgendamento, String observacoes, Long guiaExameId) {
        this.id = id;
        this.dataHoraAgendamento = dataHoraAgendamento;
        this.status = StatusAgendamentoExame.AGUARDANDO_CONFIRMACAO;
        this.observacoes = observacoes;
        this.guiaExameId = guiaExameId;
    }

    public boolean validarAgendamento () {
        return this.status != StatusAgendamentoExame.CONCLUIDO &&
                this.status != StatusAgendamentoExame.CANCELADO;
    }


    public void validarReagedamento (LocalDateTime novaData) {
        if (this.status == StatusAgendamentoExame.CANCELADO ||
                this.status == StatusAgendamentoExame.CONCLUIDO) {
            throw new IllegalStateException("Não pode reagendar");
        }

        if (novaData.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data inválida");
        }

        this.dataHoraAgendamento = novaData;
    }

    public void cancelarAgendamento () {
        if (this.status == StatusAgendamentoExame.CANCELADO) {
            throw new IllegalStateException("Já está cancelado");
        }

        this.status = StatusAgendamentoExame.CANCELADO;
    }

    public void concluirAgendamento () {
        if (this.status != StatusAgendamentoExame.AGUARDANDO_CONFIRMACAO) {
            throw new IllegalStateException("Somente exames aguardando confirmação podem ser concluídos");
        }

        this.status = StatusAgendamentoExame.CONCLUIDO;
    }
}
