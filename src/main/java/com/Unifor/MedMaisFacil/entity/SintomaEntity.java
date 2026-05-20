package com.Unifor.MedMaisFacil.entity;

import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_SINT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SintomaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SINT")
    private Long id;

    @Column(name = "DESC_SINT", nullable = false, unique = true, length = 100)
    private String descricao;

    @Column(name = "PRIO_SINT")
    @Enumerated(EnumType.STRING)
    private PrioridadeChamado prioridadeSintoma;

    @Column
    private boolean ativo;
}
