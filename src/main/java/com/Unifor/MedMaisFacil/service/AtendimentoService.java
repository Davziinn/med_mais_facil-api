package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.exceptions.*;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private MedicoService medicoService;

    @Auditable(acao = "Iniciou um atendimento", modulo = "Atendimentos")
    public Atendimento iniciar(Long chamadoId) {

        Chamado chamadoEncontrado = chamadoService.consultarDetalhesChamado(chamadoId);

        Medico medicoLogado = medicoService.buscarMedicoLogado();

        if (!chamadoEncontrado.getStatusChamado().equals(StatusChamado.EM_ESPERA)) {
            throw new ChamadoNotAvailableException("Chamado não está disponível para atendimento");
        }

        if (existePorChamadoId(chamadoId)) {
            throw new RuntimeException("Já existe um atendimento para esse chamado");
        }

        chamadoService.atualizarStatus(chamadoId, StatusChamado.EM_ATENDIMENTO);
        chamadoEncontrado.setStatusChamado(StatusChamado.EM_ATENDIMENTO);

        Atendimento atendimentoCriado = Atendimento.builder()
                .chamado(chamadoEncontrado)
                .medico(medicoLogado)
                .dataInicio(LocalDateTime.now())
                .anamnese(null)
                .build();

        return atendimentoMapper.toModel(
                atendimentoRepository.save(atendimentoMapper.toEntity(atendimentoCriado)));
    }

    @Auditable(acao = "Salvou um atendimento", modulo = "Atendimentos")
    public Atendimento salvar(Long atendimentoId, Atendimento atendimento) {
        Atendimento atendimentoEncontrado = buscarAtendimentoById(atendimentoId);

        validarMedicoDonoDoAtendimento(atendimentoEncontrado);

        Chamado chamado = chamadoService.consultarDetalhesChamado(
                atendimentoEncontrado.getChamado().getId()
        );

        if (!chamado.getStatusChamado().equals(StatusChamado.EM_ATENDIMENTO)) {
            throw new ChamadoNotAvailableException("Chamado que não está em atendimento, não pode ser salvo");
        }

        Atendimento objetoAtendimentoCriado = atendimento.toBuilder()
                .id(atendimentoEncontrado.getId())
                .dataInicio(atendimentoEncontrado.getDataInicio())
                .medico(atendimentoEncontrado.getMedico())
                .anamnese(atendimento.getAnamnese())
                .exameFisico(atendimento.getExameFisico())
                .hipoteseDiagnostica(atendimento.getHipoteseDiagnostica())
                .cidDoenca(atendimento.getCidDoenca())
                .conduta(atendimento.getConduta())
                .chamado(chamado)
                .build();

        return atendimentoMapper.toModel(atendimentoRepository.save(atendimentoMapper.toEntity(objetoAtendimentoCriado)));
    }

    public Atendimento buscarAtendimentoPorChamadoId(Long chamadoId) {
        return atendimentoMapper.toModel(
                atendimentoRepository.findByChamadoId(chamadoId)
                        .orElseThrow(() -> new RuntimeException("Atendimento não encontrado para o chamado"))
        );
    }

    @Auditable(acao = "Encerrou um atendimento", modulo = "Atendimentos")
    public Atendimento encerrarAtendimento(Long id) {
        Atendimento atendimentoEncontrado = buscarAtendimentoById(id);

        validarMedicoDonoDoAtendimento(atendimentoEncontrado);

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
        return atendimentoRepository.findByHistoricoAtendimento().stream()
                .map(atendimentoMapper::toModel)
                .toList();
    }

    public List<Atendimento> buscarAtendimentosByPacienteId(Long pacienteId) {
        return atendimentoRepository.findByPacienteId(pacienteId).stream()
                .map(atendimentoMapper::toModel)
                .toList();
    }

    public Atendimento buscarAtendimentoById(Long id) {
        return atendimentoMapper.toModel(atendimentoRepository.findById(id).orElseThrow(
                () -> new AtendimentoNotFoundException("Atendimento não encontrado")
        ));
    }

    public boolean existePorChamadoId(Long chamadoId) {
        return atendimentoRepository.existsByChamadoId(chamadoId);
    }

    public HistoricoMetricas consultarHistoricoMetricasTrimestral() {
        long totalNoPeriodo = contarTotalAtendimentosNoPeriodo();
        long totalFinalizadosNoPerido = contarAtendimentosFinalizadosNoPeriodo();
        long totalCanceladosNoPerido = contarTotalAtendimentosCanceladosNoPeriodo();

        return new HistoricoMetricas(totalNoPeriodo, totalFinalizadosNoPerido, totalCanceladosNoPerido);
    }

    /**
     * Métricas de histórico (total, finalizados, cancelados)
     * escopadas ao médico logado, no período informado em meses.
     */
    public HistoricoMetricas consultarHistoricoMetricasDoMedicoLogado(int meses) {
        Medico medicoLogado = medicoService.buscarMedicoLogado();
        Long medicoId = medicoLogado.getId();

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicioPeriodo = agora.minusMonths(meses);

        long totalNoPeriodo = atendimentoRepository
                .countByMedicoIdAndDataInicioBetween(medicoId, inicioPeriodo, agora);

        long totalFinalizadosNoPeriodo = atendimentoRepository
                .countByMedicoIdAndChamadoStatusAndDataInicioBetween(
                        medicoId, StatusChamado.FINALIZADO, inicioPeriodo, agora);

        long totalCanceladosNoPeriodo = atendimentoRepository
                .countByMedicoIdAndChamadoStatusAndDataInicioBetween(
                        medicoId, StatusChamado.CANCELADO, inicioPeriodo, agora);

        return new HistoricoMetricas(totalNoPeriodo, totalFinalizadosNoPeriodo, totalCanceladosNoPeriodo);
    }

    /**
     * Lista de atendimentos (finalizados/cancelados) do médico logado, no período informado em meses.
     * Usado para montar os cards do histórico ("Davi Pereira Menezes", diagnóstico, etc).
     */
    public List<Atendimento> buscarHistoricoDoMedicoLogado(int meses) {
        Medico medicoLogado = medicoService.buscarMedicoLogado();

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicioPeriodo = agora.minusMonths(meses);

        return atendimentoRepository.findHistoricoDoMedico(
                        medicoLogado.getId(),
                        List.of(StatusChamado.FINALIZADO, StatusChamado.CANCELADO),
                        inicioPeriodo, agora)
                .stream()
                .map(atendimentoMapper::toModel)
                .toList();
    }

    public List<AdmDashboardAtendimentosPorDia> atendimentosUltimosSeteDias() {
        LocalDateTime fim = LocalDateTime.now();
        LocalDateTime inicio = fim.minusDays(6).toLocalDate().atStartOfDay();

        List<Object[]> resultado = atendimentoRepository.contarAtendimentosPorDia(inicio, fim);

        Map<Integer, String> nomeDia = Map.of(
                0, "Dom", 1, "Seg", 2, "Ter", 3, "Qua",
                4, "Qui", 5, "Sex", 6, "Sáb"
        );

        Map<Integer, Long> porDia = new LinkedHashMap<>();
        for (int i = 6; i >= 0; i--) {
            LocalDate dia = LocalDate.now().minusDays(i);
            int numeroDia = dia.getDayOfWeek().getValue() % 7;
            porDia.put(numeroDia, 0L);
        }

        for (Object[] row : resultado) {
            int numeroDia = ((Number) row[0]).intValue();
            long count = ((Number) row[1]).longValue();
            porDia.put(numeroDia, count);
        }

        return porDia.entrySet().stream()
                .map(e -> new AdmDashboardAtendimentosPorDia(nomeDia.get(e.getKey()), e.getValue()))
                .toList();
    }

    /**
     * Garante que o médico logado é o mesmo vinculado ao atendimento antes de
     * permitir salvar ou encerrar. Evita que um médico edite/finalize
     * atendimento de outro colega.
     */
    private void validarMedicoDonoDoAtendimento(Atendimento atendimento) {
        Medico medicoLogado = medicoService.buscarMedicoLogado();
        if (!atendimento.getMedico().getId().equals(medicoLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para alterar este atendimento");
        }
    }

    private long contarTotalAtendimentosNoPeriodo() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime tresMesesAtras = agora.minusMonths(3);

        return atendimentoRepository.countByDataInicioBetween(tresMesesAtras, agora);
    }

    private long contarAtendimentosFinalizadosNoPeriodo() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime tresMesesAtras = agora.minusMonths(3);

        return atendimentoRepository.countByDataFimBetween(tresMesesAtras, agora);
    }

    private long contarTotalAtendimentosCanceladosNoPeriodo() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime tresMesesAtras = agora.minusMonths(3);

        return chamadoService.contarQuantidadeChamadosByStatusChamado(StatusChamado.CANCELADO, tresMesesAtras, agora);
    }

}