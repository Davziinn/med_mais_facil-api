package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.StatusGuiaMedica;
import com.Unifor.MedMaisFacil.mapper.GuiaMedicaMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.GuiaMedica;
import com.Unifor.MedMaisFacil.repository.GuiaMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GuiaMedicaService {

    private final GuiaMedicaRepository guiaMedicaRepository;
    private final GuiaMedicaMapper guiaMedicaMapper;

    private final AtendimentoService atendimentoService;

    public GuiaMedica salvarGuiaMedica(GuiaMedica dadosGuiaMedica) {
        Atendimento atendimentoEncontrado = atendimentoService.buscarAtendimentoById(dadosGuiaMedica.getAtendimento().getId());

        dadosGuiaMedica = dadosGuiaMedica.toBuilder()
                .dataSolicitacao(LocalDate.now())
                .statusGuiaMedica(StatusGuiaMedica.PENDENTE)
                .atendimento(atendimentoEncontrado)
                .build();
        return null;
    }
}
