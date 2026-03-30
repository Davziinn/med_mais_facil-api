package com.Unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_GUIA_EXAME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GuiaExameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GUIA_EXAM")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guia_id", nullable = false)
    private GuiaMedicaEntity guia;

    @ManyToOne
    @JoinColumn(name = "exame_id", nullable = false)
    private ExameEntity exame;
}
