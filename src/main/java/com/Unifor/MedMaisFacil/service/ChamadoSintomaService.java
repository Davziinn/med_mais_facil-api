package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.ChamadoSintomaEntity;
import com.Unifor.MedMaisFacil.exceptions.SintomaNotFoundException;
import com.Unifor.MedMaisFacil.mapper.ChamadoSintomaMapper;
import com.Unifor.MedMaisFacil.mapper.SintomaMapper;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.ChamadoSintoma;
import com.Unifor.MedMaisFacil.models.Sintoma;
import com.Unifor.MedMaisFacil.repository.ChamadoSintomaRepository;
import com.Unifor.MedMaisFacil.repository.SintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChamadoSintomaService {

    @Autowired
    private ChamadoSintomaRepository chamadoSintomaRepository;

    @Autowired
    private ChamadoSintomaMapper chamadoSintomaMapper;

    @Autowired
    private SintomaRepository sintomaRepository;

    @Autowired
    private SintomaMapper sintomaMapper;

    public List<ChamadoSintoma> salvarSintomas(Chamado chamado, List<Sintoma> sintomas) {
        if (sintomas == null || sintomas.isEmpty()) return null;

        List<ChamadoSintoma> chamadoSintomas = new ArrayList<>();

        for (Sintoma sintoma : sintomas) {
            Sintoma sintomaBuscado = sintomaMapper.toModel(sintomaRepository.findById(sintoma.getId()).orElseThrow(
                    () -> new SintomaNotFoundException("Sintoma não encontrado")
            ));

            ChamadoSintoma chamadoSintoma = ChamadoSintoma.builder()
                    .chamadoId(chamado.getId())
                    .sintoma(sintomaBuscado)
                    .dataRegistro(LocalDateTime.now())
                    .build();

            chamadoSintomas.add(chamadoSintoma);
        }

        List<ChamadoSintomaEntity> entity = chamadoSintomaRepository.saveAll(chamadoSintomas.stream().map(chamadoSintomaMapper::toEntity).toList());
        return chamadoSintomaMapper.toModelList(entity);

    }

}
