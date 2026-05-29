package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.exceptions.ChamadoNotFoundException;
import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.repository.ChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FilaService {

    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;

    public String gerarSenha(Chamado chamado) {
        List<Chamado> fila = new ArrayList<>(
                chamadoRepository.findFilaByHospital(chamado.getHospital().getId(),
                                List.of(StatusChamado.EM_ESPERA, StatusChamado.AGUARDANDO_CHECKIN)).stream()
                        .map(chamadoMapper::toModel)
                        .toList()
        );

        fila.sort(Comparator
                .comparingInt((Chamado c) -> c.getPrioridadeChamado().getOrdem())
                .thenComparing(Chamado::getDataHoraChamado)
        );

        for (int i = 0; i < fila.size(); i++) {
            if (fila.get(i).getId().equals(chamado.getId())) {
                return "A" + String.format("%03d", i + 1);
            }
        }

        throw new ChamadoNotFoundException("Chamado de ID " + chamado.getId() + " não encontrado na fila do hospital");
    }
}
