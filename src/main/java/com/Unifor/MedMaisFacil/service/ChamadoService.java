package com.Unifor.MedMaisFacil.service;

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

    private final SinaisAlertaService sinaisAlertaService;

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
        List<ChamadoEntity> entidades = chamadoRepository
                .buscarChamadosAtivosComSintomas(
                        List.of(StatusChamado.CANCELADO, StatusChamado.FINALIZADO)
                );

        return entidades.stream()
                .map(chamadoMapper::toModel)
                .toList();
    }

    public List<Chamado> listarTodosChamadosEmEsperaAndEmAtendimento() {
        List<ChamadoEntity> entidades = chamadoRepository
                .buscarChamadosAtivosComSintomas(
                        List.of(StatusChamado.CANCELADO, StatusChamado.FINALIZADO)
                ).stream()
                .filter(chamado -> chamado.getStatusChamado().equals(StatusChamado.EM_ESPERA) || chamado.getStatusChamado().equals(StatusChamado.EM_ATENDIMENTO))
                .toList();

        return entidades.stream()
                .map(chamadoMapper::toModel)
                .toList();
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

    public void marcarAusente(Long chamadoId) {
        ChamadoEntity chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() ->
                        new ChamadoNotFoundException("Chamado não encontrado"));

        validarMarcacaoAusencia(chamado);

        chamado.setStatusChamado(StatusChamado.AUSENTE);

        chamadoRepository.save(chamado);
    }

    private void validarMarcacaoAusencia(ChamadoEntity chamado) {

        if (chamado.getStatusChamado() == StatusChamado.FINALIZADO) {
            throw new RegraNegocioException("Não é possível marcar chamado finalizado como ausente");
        }
    }
}
