package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.models.Chamado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FilaService {

    @Autowired
    private HospitalService hospitalService;

    public int gerarSenha(Chamado chamado) {
        List<Chamado> fila = new ArrayList<>(
                hospitalService.buscarFilaPorHospital(chamado.getHospital().getId())
        );

        fila.sort(Comparator
                .comparingInt((Chamado c) -> c.getPrioridadeChamado().getOrdem())
                .thenComparing(Chamado::getDataHoraChamado)
        );

        for (int i = 0; i < fila.size(); i++) {
            if (fila.get(i).getId().equals(chamado.getId())) {
                return i + 1;
            }
        }

        throw new RuntimeException("Chamado de ID " + chamado.getId() + " não encontrado na fila do hospital");
    }
}
