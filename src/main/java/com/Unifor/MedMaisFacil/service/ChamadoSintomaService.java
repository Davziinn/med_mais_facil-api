package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.ChamadoSintomaEntity;
import com.Unifor.MedMaisFacil.exceptions.ChamadoNotFoundException;
import com.Unifor.MedMaisFacil.exceptions.SintomaNotFoundException;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    public List<ChamadoSintoma> salvarSintomas(Chamado chamado, List<SintomaDoChamado> sintomas) {
        if (sintomas == null || sintomas.isEmpty()) {
            return List.of();
        }

        List<ChamadoSintoma> chamadoSintomas = new ArrayList<>();

        for (SintomaDoChamado sintomaDochamado : sintomas) {
            Sintoma sintomaBuscado = sintomaMapper.toModel(
                    sintomaRepository.findById(sintomaDochamado.sintomaId()).orElseThrow(
                            () -> new SintomaNotFoundException("Sintoma não encontrado")
                    )
            );

            ChamadoSintoma chamadoSintoma = ChamadoSintoma.builder()
                    .chamado(chamado)
                    .sintoma(sintomaBuscado)
                    .intensidade(sintomaDochamado.intensidade())
                    .tempoSintoma(sintomaDochamado.tempoSintoma())
                    .frequencia(sintomaDochamado.frequencia())
                    .dataRegistro(LocalDateTime.now())
                    .build();

            chamadoSintomas.add(chamadoSintoma);
        }

        List<ChamadoSintomaEntity> salvas = chamadoSintomaRepository.saveAll(chamadoSintomas.stream().map(chamadoSintomaMapper::toEntity).toList());

        return salvas.stream().map(chamadoSintomaMapper::toModel).toList();
    }

    public ChamadoSintoma buscarDetalhesSintomaById (Long id) {
        if (id == null) return null;

        ChamadoSintomaEntity chamadoBuscado = chamadoSintomaRepository.findById(id).orElseThrow(
                () -> new ChamadoNotFoundException("Chamado não encontrado")
        );

        return chamadoSintomaMapper.toModel(chamadoBuscado);
    }

}
