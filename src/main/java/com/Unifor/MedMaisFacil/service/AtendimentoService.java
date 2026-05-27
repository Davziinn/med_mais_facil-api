package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.exceptions.*;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private MedicoRepository medicoRepository;

    @Auditable(acao = "Iniciou um atendimento", modulo = "Atendimentos")
    public Atendimento iniciar(Long chamadoId, Long medicoId) {

        Chamado chamadoEncontrado = chamadoService.consultarDetalhesChamado(chamadoId);

        MedicoEntity medicoEncontrado = medicoRepository.findById(medicoId)
                        .orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado"));

        if (!chamadoEncontrado.getStatusChamado().equals(StatusChamado.EM_ESPERA)) {
            throw new ChamadoNotAvailableException("Chamado não está disponível para atendimento");
        }

        if (existePorChamadoId(chamadoId)) {
            throw new RuntimeException("Já existe um atendimento para esse chamado");
        }


        chamadoService.atualizarStatus(chamadoId, StatusChamado.EM_ATENDIMENTO);

        chamadoEncontrado.setStatusChamado(StatusChamado.EM_ATENDIMENTO);

        Medico medico = medicoMapper.toModel(medicoEncontrado);

        Atendimento atendimentoCriado = Atendimento.builder()
                .chamado(chamadoEncontrado)
                .medico(medico)
                .dataInicio(LocalDateTime.now())
                .anamnese(null)
                .build();

        return atendimentoMapper.toModel(atendimentoRepository.save(atendimentoMapper.toEntity(atendimentoCriado)));
    }

    @Auditable(acao = "Salvou um atendimento", modulo = "Atendimentos")
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

    @Auditable(acao = "Encerrou um atendimento", modulo = "Atendimentos")
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

    public List<Atendimento> buscarHistoricoAtendimentos() {
        List<Atendimento> historicosEncontrados = atendimentoRepository.findByHistoricoAtendimento().stream()
                .map(atendimentoMapper::toModel)
                .toList();

        return historicosEncontrados;
    }

    public List<Atendimento> buscarAtendimentosByPacienteId(Long pacienteId) {
        List<Atendimento> atendimentosEncontrados = atendimentoRepository.findByPacienteId(pacienteId).stream()
                .map(atendimentoMapper::toModel)
                .toList();

        return atendimentosEncontrados;
    }

    public Atendimento buscarAtendimentoById (Long id) {
        return atendimentoMapper.toModel(atendimentoRepository.findById(id).orElseThrow(
                () -> new AtendimentoNotFoundException("Atendimento não encontrado")
        ));
    }

    public boolean existePorChamadoId (Long chamadoId) {
        return atendimentoRepository.existsByChamadoId(chamadoId);
    }

    public HistoricoMetricas consultarHistoricoMetricasTrimestral () {
        long totalNoPeriodo = contarTotalAtendimentosNoPeriodo();
        long totalFinalizadosNoPerido = contarAtendimentosFinalizadosNoPeriodo();
        long totalCanceladosNoPerido = contarTotalAtendimentosCanceladosNoPeriodo();
        double taxaCancelamento = calcularTaxaCancelamento();

        return new HistoricoMetricas(totalNoPeriodo, totalFinalizadosNoPerido, totalCanceladosNoPerido, taxaCancelamento);
    }

    private long contarTotalAtendimentosNoPeriodo () {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime tresMesesAtras = agora.minusMonths(3);

        return atendimentoRepository.countByDataInicioBetween(tresMesesAtras, agora);
    }

    private long contarAtendimentosFinalizadosNoPeriodo () {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime tresMesesAtras = agora.minusMonths(3);

        return atendimentoRepository.countByDataFimBetween(tresMesesAtras, agora);
    }

    private long contarTotalAtendimentosCanceladosNoPeriodo () {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime tresMesesAtras = agora.minusMonths(3);

        return chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.CANCELADO, tresMesesAtras, agora);
    }

    private int calcularTaxaCancelamento() {

        long totalNoPeriodo =
                contarTotalAtendimentosNoPeriodo();

        long cancelamentosNoPeriodo =
                contarTotalAtendimentosCanceladosNoPeriodo();

        if (totalNoPeriodo == 0) {
            return 0;
        }

        double taxa = ((double) cancelamentosNoPeriodo / totalNoPeriodo) * 100;

        return (int) Math.round(taxa);
    }

}
