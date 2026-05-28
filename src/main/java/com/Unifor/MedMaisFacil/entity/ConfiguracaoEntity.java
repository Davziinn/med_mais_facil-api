package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CONFIG")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConfiguracaoEntity {

    @Id
    @Column(name = "ID_CONFIG")
    private Long id;

    @Column(name = "TMP_LMT_CHAM", nullable = false)
    private Integer tempoLimiteChamado;

    @Column(name = "QNTD_MAX_FILA", nullable = false)
    private Integer quantidadeMaximaFila;

    @Column(name = "CHAM_AUT", nullable = false)
    private Boolean chamadaAutomatica;

    @Column(name = "STS_GRL", nullable = false)
    private String statusGeral;

    @Column(name = "MSG_PAC", nullable = false)
    private String mensagemPaciente;

    @Column(name = "NTFC_PUSH", nullable = false)
    private Boolean notificacoesPush;
}
