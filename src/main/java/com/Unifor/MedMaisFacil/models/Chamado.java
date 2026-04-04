package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Chamado {

    private Long id;
    private String descricaoRelato;
    private StatusChamado statusChamado;
    private PrioridadeChamado prioridadeChamado;
    private LocalDateTime dataHoraChamado;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private Paciente paciente;
    private Hospital hospital;

    public Chamado (Long id, String descricaoRelato, LocalDateTime dataHoraChamado, Paciente paciente, Hospital hospital) {
        this.id = id;
        this.descricaoRelato = descricaoRelato;
        this.statusChamado =  StatusChamado.EM_ESPERA;
        this.prioridadeChamado = PrioridadeChamado.MEDIA; // Default -> Padrão
        this.dataHoraChamado = dataHoraChamado;
        this.paciente = paciente;
        this.hospital = hospital;
    }

    public void definirPrioridade (PrioridadeChamado prioridadeChamado) {
        if (prioridadeChamado == null) {
            throw new IllegalArgumentException("Prioridade inválida");
        }

        this.prioridadeChamado = prioridadeChamado;
    }

    public void iniciarChamado (StatusChamado statusChamado) {
        if (this.statusChamado != StatusChamado.EM_ESPERA) {
            throw new IllegalArgumentException("Status do chamado não está EM_ESPERA");
        }
        this.statusChamado = StatusChamado.EM_ATENDIMENTO;
    }

    public void finalizarChamado (StatusChamado statusChamado) {
        if (statusChamado != StatusChamado.EM_ATENDIMENTO) {
            throw new IllegalArgumentException("Status do chamado não está EM_ATENDIMENTO");
        }

        this.statusChamado = StatusChamado.FINALIZADO;
    }

    public void cancelarAtendimento (StatusChamado statusChamado) {
        if (this.statusChamado == StatusChamado.FINALIZADO) {
            throw new IllegalArgumentException("Chamado já FINALIZADO não pode ser CANCELADO");
        }

        this.statusChamado = StatusChamado.CANCELADO;
    }
}
