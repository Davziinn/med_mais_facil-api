package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_LOG_AUDT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LogsAuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOG")
    private Long id;

    @Column(name = "ID_USU")
    private Long usuarioId;

    @Column(name = "NM_USU")
    private String nomeUsuario;

    @Column(name = "ACAO_LOG", nullable = false)
    private String acao;

    @Column(name = "MOD_LOG", nullable = false)
    private String modulo;

    @Column(name = "DET_LOG", columnDefinition = "text")
    private String detalhes;

    @CreationTimestamp
    @Column(name = "DT_CRI")
    private LocalDateTime criadoEm;
}
