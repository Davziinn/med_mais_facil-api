package com.Unifor.MedMaisFacil.entity;

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
}
