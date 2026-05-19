package com.Unifor.MedMaisFacil.entity;

import com.Unifor.MedMaisFacil.enums.Severidade;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "TB_EVNT_CLNC")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EventoClinicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVNT")
    private Long id;

    @Column(name = "NM_EVNT" /*, nullable = false, unique = true*/)
    private String nomeEvento;

//    @Column(name = "DSC_EVNT", nullable = false)
    @Column(name = "DESC_ENVT"/*, nullable = false*/)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "SRV_EVNT")
    private Severidade severidade;
}
