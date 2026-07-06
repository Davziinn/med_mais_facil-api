package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.dtos.guiaExame.GuiaMedicaRequestDTO;
import com.Unifor.MedMaisFacil.entity.ExameEntity;
import com.Unifor.MedMaisFacil.entity.GuiaExameEntity;
import com.Unifor.MedMaisFacil.entity.GuiaMedicaEntity;
import com.Unifor.MedMaisFacil.enums.StatusGuiaMedica;
import com.Unifor.MedMaisFacil.exceptions.ErrorConsultarGuiaMedica;
import com.Unifor.MedMaisFacil.exceptions.ErrorGerarGuiaMedicaException;
import com.Unifor.MedMaisFacil.exceptions.GuiaMedicaNotFoundException;
import com.Unifor.MedMaisFacil.mapper.ExameMapper;
import com.Unifor.MedMaisFacil.mapper.GuiaExameMapper;
import com.Unifor.MedMaisFacil.mapper.GuiaMedicaMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.Exame;
import com.Unifor.MedMaisFacil.models.GuiaExame;
import com.Unifor.MedMaisFacil.models.GuiaMedica;
import com.Unifor.MedMaisFacil.repository.GuiaMedicaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private final GuiaExameMapper guiaExameMapper;
    private final ExameMapper exameMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public GuiaMedica salvarGuiaMedica(GuiaMedica dadosGuiaMedica) {

        Atendimento atendimentoEncontrado = atendimentoService.buscarAtendimentoById(dadosGuiaMedica.getAtendimento().getId());

        if (dadosGuiaMedica.getExames() == null || dadosGuiaMedica.getExames().isEmpty()) {
            throw new ErrorGerarGuiaMedicaException("É necessário selecionar ao menos um exame para gerar uma Guia Médica");
        }

        dadosGuiaMedica = dadosGuiaMedica.toBuilder()
                .dataSolicitacao(LocalDateTime.now())
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

    public List<GuiaMedica> consultarGuiasAtendimento(Long atendimentoId) {
        List<GuiaMedica> guiasEncotradas = guiaMedicaRepository.findAllByAtendimentoId(atendimentoId)
                .orElseThrow(() -> new ErrorConsultarGuiaMedica("Erro ao consultar as guias dos exames para esse atendimento"))
                .stream()
                .map(guiaMedicaMapper::toModel)
                .toList();



        return guiasEncotradas;
    }

    public GuiaMedica buscarGuiaMedicaById (Long id) {
        return guiaMedicaMapper.toModel(guiaMedicaRepository.findById(id).orElseThrow(
                () -> new GuiaMedicaNotFoundException("Guia Medica não encontrada")
        ));
    }

    @Transactional
    public GuiaMedica editarGuiaMedica(Long id, GuiaMedicaRequestDTO dto) {
        GuiaMedicaEntity guiaEntity = guiaMedicaRepository.findById(id)
                .orElseThrow(() -> new GuiaMedicaNotFoundException("Guia não encontrada: " + id));

        // Atualiza campos simples
        if (dto.cidExame() != null) guiaEntity.setCidExame(dto.cidExame());
        if (dto.indicacaoClinica() != null) guiaEntity.setIndicacaoClinica(dto.indicacaoClinica());
        if (dto.observacoes() != null) guiaEntity.setObservacoes(dto.observacoes());
        if (dto.convenio() != null) guiaEntity.setConvenio(dto.convenio());

        guiaMedicaRepository.save(guiaEntity);

        // Atualiza exames: deleta os antigos e recria
        if (dto.examesIds() != null && !dto.examesIds().isEmpty()) {
            guiaExameSerivce.deletarTudoPeloGuiaId(id);

            List<GuiaExameEntity> novosGuiaExames = dto.examesIds().stream()
                    .map(exameId -> {
                        ExameEntity exame = exameMapper.toEntity(exameService.buscarExameById(exameId));
                        return GuiaExameEntity.builder()
                                .guia(guiaEntity)
                                .exame(exame)
                                .build();
                    })
                    .toList();

            guiaExameSerivce.salvarGuiaExame(novosGuiaExames.stream().map(guiaExameMapper::toModel).toList());
        }

        entityManager.clear();

        GuiaMedicaEntity guiaAtualizada = guiaMedicaRepository.findById(id)
                .orElseThrow(() -> new GuiaMedicaNotFoundException("Guia não encontrada: " + id));


        return guiaMedicaMapper.toModel(guiaAtualizada);
    }

    private String gerarNumeroGuia() {
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String sufixo = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "GUIA-" + data + "-" + sufixo;
    }

    public GuiaMedica cancelarGuiaMedica(Long id) {
        GuiaMedica encontrada = buscarGuiaMedicaById(id);

        encontrada = encontrada.toBuilder()
                .statusGuiaMedica(StatusGuiaMedica.CANCELADA)
                .build();

        return guiaMedicaMapper.toModel(guiaMedicaRepository.save(guiaMedicaMapper.toEntity(encontrada)));
    }
}
