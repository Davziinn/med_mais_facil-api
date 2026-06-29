package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;
import jakarta.persistence.Table;

import java.time.*;

@Entity
@Table(name = "TB_EXAM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXAM")
    private Long id;

    @Column(name = "NM_EXAM", nullable = false, unique = true)
    private String nome;

    @Column(name = "DESC_EXAM")
    private String descricao;

    @Column(name = "EXAM_ATV")
    private Boolean ativo;

    @CreationTimestamp
    @Column(name = "DT_CRI_EXAM", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "DT_ATZ_EXAM")
    private LocalDateTime atualizadoEm;
}
