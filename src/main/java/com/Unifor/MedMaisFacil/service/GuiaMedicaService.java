package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.StatusGuiaMedica;
import com.Unifor.MedMaisFacil.exceptions.ErrorGerarGuiaMedicaException;
import com.Unifor.MedMaisFacil.mapper.GuiaMedicaMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.Exame;
import com.Unifor.MedMaisFacil.models.GuiaExame;
import com.Unifor.MedMaisFacil.models.GuiaMedica;
import com.Unifor.MedMaisFacil.repository.GuiaMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GuiaMedicaService {

    private final GuiaMedicaRepository guiaMedicaRepository;
    private final GuiaMedicaMapper guiaMedicaMapper;

    private final AtendimentoService atendimentoService;

    private final ExameService exameService;

    private final GuiaExameSerivce guiaExameSerivce;

    @Transactional
    public GuiaMedica salvarGuiaMedica(GuiaMedica dadosGuiaMedica) {

        Atendimento atendimentoEncontrado = atendimentoService.buscarAtendimentoById(dadosGuiaMedica.getAtendimento().getId());

        if (dadosGuiaMedica.getExames() == null || dadosGuiaMedica.getExames().isEmpty()) {
            throw new ErrorGerarGuiaMedicaException("É necessário selecionar ao menos um exame para gerar uma Guia Médica");
        }

        dadosGuiaMedica = dadosGuiaMedica.toBuilder()
                .dataSolicitacao(LocalDate.now())
                .numeroGuia(gerarNumeroGuia())
                .statusGuiaMedica(StatusGuiaMedica.PENDENTE)
                .atendimento(atendimentoEncontrado)
                .build();

        GuiaMedica guiaMedicaSalva = guiaMedicaMapper.toModel(guiaMedicaRepository.save(guiaMedicaMapper.toEntity(dadosGuiaMedica)));

        List<GuiaExame> guiaExames = dadosGuiaMedica.getExames().stream()
                .map(exame -> {
                    Exame exameEncontrado = exameService.buscarExameById(exame.getId());

                    return GuiaExame.builder()
                            .guia(guiaMedicaSalva)
                            .exame(exameEncontrado)
                            .build();
                })
                .toList();

        guiaExameSerivce.salvarGuiaExame(guiaExames);

        return guiaMedicaSalva.toBuilder()
                .exames(
                        guiaExames.stream()
                                .map(GuiaExame::getExame)
                                .toList()
                )
                .build();
    }

    private String gerarNumeroGuia() {
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String sufixo = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "GUIA-" + data + "-" + sufixo;
    }
}
