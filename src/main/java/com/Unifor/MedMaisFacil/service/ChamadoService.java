package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.entity.*;
import com.Unifor.MedMaisFacil.enums.*;
import com.Unifor.MedMaisFacil.exceptions.ChamadoNotFoundException;
import com.Unifor.MedMaisFacil.exceptions.RegraNegocioException;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;
    private final ChamadoSintomaRepository chamadoSintomaRepository;

    private final ChamadoEventoClinicoRepository chamadoEventoClinicoRepository;
    private final ChamadoEventoClinicoMapper chamadoEventoClinicoMapper;

    private final ChamadoSintomaMapper chamadoSintomaMapper;

    private final PacienteService pacienteService;
    private final HospitalService hospitalService;
    private final ChamadoSintomaService chamadoSintomaService;
    private final TriagemService triagemService;
    private final FilaService filaService;

    private final MedicoService medicoService;

    private final SinaisAlertaService sinaisAlertaService;

    private final EspecialidadeService especialidadeService;

    public ChamadoAberto abrirChamado (Chamado chamado, List<SintomaDoChamado> sintomas) {

        // 1. Buscar um paciente
        Paciente pacienteEncontrato = pacienteService.buscarPacienteById(chamado.getPaciente().getId());
        // 2. Buscar um hospital (Por enquanto, vai buscar por ID, depois altero para buscar o mais próximo do paciente)
        Hospital hospitalEncontrado = hospitalService.buscarHospitalById(chamado.getHospital().getId());
        // 3. Classificar prioridade
        PrioridadeChamado prioridadeChamadoCalculado = triagemService.calcularPrioridade(chamado);
        // 4. Criar um chamado
        Chamado chamadoCriado = registrarChamado(chamado, pacienteEncontrato, hospitalEncontrado, prioridadeChamadoCalculado);
        // 5. Salvar os sintomas
        List<ChamadoSintoma> listaSintomas = chamadoSintomaService.salvarSintomas(chamadoCriado, sintomas);
        // 6. Gerar senha / Fila
        String senhaGerada = filaService.gerarSenha(chamadoCriado);

        return new ChamadoAberto(chamadoCriado, listaSintomas, prioridadeChamadoCalculado, senhaGerada);
    }

    public Chamado registrarChamado(Chamado chamado, Paciente paciente, Hospital hospital, PrioridadeChamado prioridadeChamado) {
        Chamado vinculaAoChamado = chamado.toBuilder()
                .prioridadeChamado(prioridadeChamado)
                .paciente(paciente)
                .hospital(hospital)
                .statusChamado(StatusChamado.AGUARDANDO_CHECKIN)
                .dataHoraChamado(LocalDateTime.now())
                .build();

        return chamadoMapper.toModel(chamadoRepository.save(chamadoMapper.toEntity(vinculaAoChamado)));
    }

    /*
        Busca os chamados no banco
        Busca os sintomas no banco
        Popula manualmente o model do Chamado com o Sintoma vindo do banco
    */
    public Chamado consultarDetalhesChamado(Long id) {
        ChamadoEntity chamadoEntity = chamadoRepository.findById(id)
                .orElseThrow(() -> new ChamadoNotFoundException("Chamado não encontrado"));

        List<ChamadoSintomaEntity> sintomaEntities = chamadoSintomaRepository.findByChamadoId(id);
        List<ChamadoEventoClinicoEntity> eventosEncontradosParaEsseChamado = chamadoEventoClinicoRepository.findByChamadoId(id);

        Chamado model = chamadoMapper.toModel(chamadoEntity);
        model.setChamadoSintomas(chamadoSintomaMapper.toModelList(sintomaEntities));
        model.setChamadoEventoClinicos(chamadoEventoClinicoMapper.toModelList(eventosEncontradosParaEsseChamado));
        model.setSinaisAlertas(sinaisAlertaService.gerarSinaisAlerta(chamadoEventoClinicoMapper.toModelList(eventosEncontradosParaEsseChamado), chamadoSintomaMapper.toModelList(sintomaEntities)));

        return model;
    }

    /*
        Busca do banco os Chamados e os Sintomas relacionados
    */
    public List<Chamado> listarTodosChamadosAtivos() {
        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDia = hoje.atStartOfDay();
        LocalDateTime fimDia = hoje.atTime(LocalTime.MAX);

        List<ChamadoEntity> entidades = chamadoRepository
                .buscarChamadosAtivosComSintomasAndDataHoraChamadoBetween(
                        List.of(StatusChamado.CANCELADO, StatusChamado.FINALIZADO), inicioDia, fimDia
                );

        return entidades.stream()
                .map(chamadoMapper::toModel)
                .toList();
    }

    private List<Chamado> buscarFilaDoDia(Long especialidadeIdOuNull) {
        LocalDate hoje = LocalDate.now();
        LocalDateTime inicioDia = hoje.atStartOfDay();
        LocalDateTime fimDia = hoje.atTime(LocalTime.MAX);

        return chamadoRepository
                .buscarChamadosAtivosComSintomasAndDataHoraChamadoBetween(
                        List.of(StatusChamado.CANCELADO, StatusChamado.FINALIZADO), inicioDia, fimDia
                ).stream()
                .filter(chamado -> chamado.getStatusChamado().equals(StatusChamado.EM_ESPERA) || chamado.getStatusChamado().equals(StatusChamado.EM_ATENDIMENTO) || chamado.getStatusChamado().equals(StatusChamado.AGUARDANDO_ENCAMINHAMENTO))
                .filter(chamado -> especialidadeIdOuNull == null || (chamado.getEspecialidadeDestino() != null && chamado.getEspecialidadeDestino().getId().equals(especialidadeIdOuNull)))
                .map(chamadoMapper::toModel)
                .toList();
    }

    public List<Chamado> listarTodosChamadosEmEsperaAndEmAtendimento() {
        return buscarFilaDoDia(null);
    }

    public List<Chamado> listarChamadosParaMedicoLogado() {
        Medico medicoLogado = medicoService.buscarMedicoLogado();
        return buscarFilaDoDia(medicoLogado.getEspecialidade().getId());
    }

    public void atualizarStatus(Long chamadoId, StatusChamado novoStatus) {
        ChamadoEntity chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new ChamadoNotFoundException("Chamado não encontrado"));

        chamado.setStatusChamado(novoStatus);
        chamadoRepository.save(chamado);
    }

    public long contarQuantidadeChamadosByStatusChamado (StatusChamado statusChamado) {
        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX);

        return chamadoRepository.countByStatusChamadoAndDataHoraChamadoBetween(statusChamado, inicioDoDia, fimDoDia);
    }

    public long contarQuantidadeChamadosByPrioridadeChamado (PrioridadeChamado nivelPrioridade) {
        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX);

        return chamadoRepository.countByPrioridadeChamadoAndDataHoraChamadoBetween(nivelPrioridade, inicioDoDia, fimDoDia);
    }

    public long contarQuantidadeChamadosByStatusChamado(
            StatusChamado statusChamado,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {

        return chamadoRepository
                .countByStatusChamadoAndDataHoraChamadoBetween(
                        statusChamado,
                        inicio,
                        fim
                );
    }

    public long contarQuantidadeChamadosByDia () {
        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX);

        return chamadoRepository.countByCriadoEmBetween(inicioDoDia, fimDoDia);
    }

    public List<Chamado> listarAguardandoEncaminhamento() {
        return chamadoRepository.findByStatusChamado(StatusChamado.AGUARDANDO_ENCAMINHAMENTO).stream()
                .map(chamadoMapper::toModel)
                .toList();
    }

    public long contarQuantidadeChamadosPorEspecialidadeDoMedicoLogado () {
        Medico medicoLogado = medicoService.buscarMedicoLogado();
        Long especialidadeId = medicoLogado.getEspecialidade().getId();

        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDia = hoje.atStartOfDay();
        LocalDateTime fimDia = hoje.atTime(LocalTime.MAX);

        return chamadoRepository.countByEspecialidadeDestino_IdAndDataHoraChamadoBetween(especialidadeId, inicioDia, fimDia);
    }

    public long contarQuantidadeChamadosPorEspecialidadeEPorStatusDoMedicoLogado (StatusChamado statusChamado) {
        Medico medicoLogado = medicoService.buscarMedicoLogado();
        Long especialidadeId = medicoLogado.getEspecialidade().getId();

        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDia = hoje.atStartOfDay();
        LocalDateTime fimDia = hoje.atTime(LocalTime.MAX);

        return chamadoRepository.countByespecialidadeDestino_IdAndStatusChamadoAndDataHoraChamadoBetween(especialidadeId, statusChamado, inicioDia, fimDia);
    }

    public List<Chamado> buscarChamadosEmAtendimentoDiaAtual () {
        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX);

        return chamadoRepository.findByStatusChamadoAndDataHoraChamadoBetweenOrderByDataHoraChamadoAsc(StatusChamado.EM_ATENDIMENTO, inicioDoDia, fimDoDia).stream()
                .map(chamadoMapper::toModel)
                .toList();
    }

    public List<Chamado> buscarPacientesAguardandoCheckin () {
        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX);

        return chamadoRepository.findByStatusChamadoAndDataHoraChamadoBetweenOrderByDataHoraChamadoAsc(StatusChamado.AGUARDANDO_CHECKIN, inicioDoDia, fimDoDia).stream()
                .map(chamadoMapper::toModel)
                .toList();
    }

    @Auditable(acao = "Marcou como ausente", modulo = "Chamados")
    public void marcarAusente(Long chamadoId) {
        ChamadoEntity chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() ->
                        new ChamadoNotFoundException("Chamado não encontrado"));

        validarMarcacaoAusencia(chamado);

        chamado.setStatusChamado(StatusChamado.AUSENTE);

        chamadoRepository.save(chamado);
    }

    @Auditable(acao = "Confirmou o checkin", modulo = "Chamados")
    public void confirmarCheckin (Long chamadoId) {
        Chamado chamadoBuscado = chamadoMapper.toModel(chamadoRepository.findById(chamadoId)
                .orElseThrow(
                        () -> new ChamadoNotFoundException("Chamado não encontrado")
                ));

        chamadoBuscado.setStatusChamado(StatusChamado.AGUARDANDO_ENCAMINHAMENTO);

        chamadoRepository.save(chamadoMapper.toEntity(chamadoBuscado));
    }

    @Auditable(acao = "Cancelou o chamado", modulo = "Chamados")
    public void cancelarChamado (Long chamadoId) {
        Chamado chamadoBuscado = chamadoMapper.toModel(chamadoRepository.findById(chamadoId)
                .orElseThrow(
                        () -> new ChamadoNotFoundException("Chamado não encontrado")
                ));

        chamadoBuscado.setStatusChamado(StatusChamado.CANCELADO);

        chamadoRepository.save(chamadoMapper.toEntity(chamadoBuscado));
    }

    @Auditable(acao = "Alterou a prioridade", modulo = "Chamados")
    public Chamado alterarPrioridade (Long chamadoId, PrioridadeChamado novaPrioridade) {
        Chamado chamadoEncontrado = chamadoMapper.toModel(chamadoRepository.findById(chamadoId).orElseThrow(
                () -> new ChamadoNotFoundException("Chamado não encontrado!")
        ));

        chamadoEncontrado = chamadoEncontrado.toBuilder()
                .prioridadeChamado(novaPrioridade)
                .build();

        return chamadoMapper.toModel(chamadoRepository.save(chamadoMapper.toEntity(chamadoEncontrado)));
    }

    @Auditable(acao = "Encaminhou um chamado", modulo = "Chamados")
    public EncaminharChamado encaminharChamado (Long chamadoId, EncaminharChamado encaminharChamado) {
        Chamado chamadoEncontrado = consultarDetalhesChamado(chamadoId);

        EspecialidadeMedico especialidadeMedicoEncontrado = especialidadeService.buscarEspecialidadeById(encaminharChamado.getEspecialidadeMedico().getId());

        chamadoEncontrado = chamadoEncontrado.toBuilder()
                .statusChamado(StatusChamado.EM_ESPERA)
                .especialidadeDestino(especialidadeMedicoEncontrado)
                .build();

        Chamado chamadoAtualizado = chamadoMapper.toModel(chamadoRepository.save(chamadoMapper.toEntity(chamadoEncontrado)));

        return new EncaminharChamado(
                chamadoAtualizado.getId(),
                chamadoAtualizado.getStatusChamado(),
                chamadoAtualizado.getEspecialidadeDestino()
        );
    }

    private void validarMarcacaoAusencia(ChamadoEntity chamado) {

        if (chamado.getStatusChamado() == StatusChamado.FINALIZADO) {
            throw new RegraNegocioException("Não é possível marcar chamado finalizado como ausente");
        }
    }

}
