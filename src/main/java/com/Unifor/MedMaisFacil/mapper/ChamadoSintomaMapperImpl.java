package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.entity.ChamadoSintomaEntity;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
*   private Long id;
    private Integer intensidade;
    private String descricaoLivre;
    private LocalDateTime dataRegistro;
    private Long chamado;
    private Sintoma sintoma;
* */

@Component
public class ChamadoSintomaMapperImpl implements ChamadoSintomaMapper {

    @Autowired
    private SintomaMapper sintomaMapper;

    @Override
    public ChamadoSintoma toModel(ChamadoSintomaEntity entity) {
        return new ChamadoSintoma(
                entity.getId(),
                entity.getIntensidade(),
                entity.getDescricaoLivre(),
                entity.getDataRegistro(),
                entity.getChamado().getId(),
                entity.getSintoma() != null
                        ? sintomaMapper.toModel(entity.getSintoma())
                        : null
        );
    }

    @Override
    public ChamadoSintomaEntity toEntity(ChamadoSintoma model) {
        ChamadoEntity referenciaDoChamado = ChamadoEntity.builder()
                .id(model.getChamadoId())
                .build();

        return ChamadoSintomaEntity.builder()
                .id(model.getId())
                .intensidade(model.getIntensidade())
                .descricaoLivre(model.getDescricaoLivre())
                .dataRegistro(model.getDataRegistro())
                .chamado(referenciaDoChamado)
                .sintoma(model.getSintoma() != null
                        ? sintomaMapper.toEntity(model.getSintoma())
                        : null
                )
                .build();
    }
}
