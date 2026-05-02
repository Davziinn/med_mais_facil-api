package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.exceptions.*;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    public Atendimento iniciar(Long chamadoId, Long medicoId) {

        Chamado chamadoEncontrado =  chamadoService.consultarDetalhesChamado(chamadoId);

        MedicoEntity medicoEncontrado = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado"));

        if (!chamadoEncontrado.getStatusChamado().equals(StatusChamado.EM_ESPERA)) {
            throw new ChamadoNotAvailableException("Chamado não está disponível para atendimento");
        }

        if (existePorChamadoId(chamadoId)) {
            throw new RuntimeException("Já existe um atendimento para esse chamado");
        }


        Medico medico = medicoMapper.toModel(medicoEncontrado);

        Atendimento atendimentoCriado = Atendimento.builder()
                .chamado(chamadoEncontrado)
                .medico(medico)
                .dataInicio(LocalDateTime.now())
                .anamnese(null)
                .build();

        Atendimento atendimentoSalvo = atendimentoMapper.toModel(
                atendimentoRepository.save(atendimentoMapper.toEntity(atendimentoCriado))
        );

        chamadoService.atualizarStatus(chamadoId, StatusChamado.EM_ATENDIMENTO);

        return atendimentoSalvo;
    }

    public Atendimento salvar (Long id, Atendimento atendimento) {
        Atendimento atendimentoEncontrado = buscarAtendimentoById(id);

        if (!atendimentoEncontrado.getChamado().getStatusChamado().equals(StatusChamado.EM_ATENDIMENTO)) {
            throw new ChamadoNotAvailableException("Chamado que não está em atendimento, não pode ser salvo");
        }

        Atendimento objetoAtendimentoCriado = atendimentoEncontrado.toBuilder()
                .anamnese(atendimento.getAnamnese())
                .exameFisico(atendimento.getExameFisico())
                .hipoteseDiagnostica(atendimento.getHipoteseDiagnostica())
                .cidDoenca(atendimento.getCidDoenca())
                .conduta(atendimento.getConduta())
                .build();

        return atendimentoMapper.toModel(atendimentoRepository.save(atendimentoMapper.toEntity(objetoAtendimentoCriado)));
    }

    public Atendimento encerrarAtendimento (Long id) {
        Atendimento atendimentoEncontrado = buscarAtendimentoById(id);

        if (atendimentoEncontrado.getDataFim() != null) {
            throw new RuntimeException("Atendimento já foi finalizado");
        }

        if (atendimentoEncontrado.getChamado().getStatusChamado() != StatusChamado.EM_ATENDIMENTO) {
            throw new ChamadoNotAvailableException("Chamado que não está em atendimento, não pode ser salvo");
        }

        Atendimento objetoEncerrarAtendimento = atendimentoEncontrado.toBuilder()
                .dataFim(LocalDateTime.now())
                .build();

        Atendimento atendimentoEncerrado = atendimentoMapper.toModel(atendimentoRepository.save(atendimentoMapper.toEntity(objetoEncerrarAtendimento)));

        chamadoService.atualizarStatus(atendimentoEncontrado.getChamado().getId(), StatusChamado.FINALIZADO);

        return atendimentoEncerrado;
    }

    public Atendimento buscarAtendimentoById (Long id) {
        return atendimentoMapper.toModel(atendimentoRepository.findById(id).orElseThrow(
                () -> new AtendimentoNotFoundException("Atendimento não encontrado")
        ));
    }

    public boolean existePorChamadoId (Long chamadoId) {
        return atendimentoRepository.existsByChamadoId(chamadoId);
    }


}
