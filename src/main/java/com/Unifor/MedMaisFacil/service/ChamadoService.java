package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.*;
import com.Unifor.MedMaisFacil.enums.*;
import com.Unifor.MedMaisFacil.exceptions.ChamadoNotFoundException;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ChamadoMapper chamadoMapper;

    @Autowired
    private ChamadoSintomaRepository chamadoSintomaRepository;

    @Autowired
    private ChamadoSintomaMapper chamadoSintomaMapper;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private ChamadoSintomaService chamadoSintomaService;

    @Autowired
    private TriagemService triagemService;

    @Autowired
    private FilaService filaService;

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
                .statusChamado(StatusChamado.EM_ESPERA)
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

        Chamado model = chamadoMapper.toModel(chamadoEntity);
        model.setChamadoSintomas(chamadoSintomaMapper.toModelList(sintomaEntities));

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

    public void atualizarStatus(Long chamadoId, StatusChamado novoStatus) {
        ChamadoEntity chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new ChamadoNotFoundException("Chamado não encontrado"));

        chamado.setStatusChamado(novoStatus);
        chamadoRepository.save(chamado);
    }
}
