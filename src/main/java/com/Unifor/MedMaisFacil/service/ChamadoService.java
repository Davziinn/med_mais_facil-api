package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.entity.ChamadoSintomaEntity;
import com.Unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.mapper.ChamadoSintomaMapper;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.ChamadoRepository;
import com.Unifor.MedMaisFacil.repository.ChamadoSintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        int senhaGerada = filaService.gerarSenha(chamadoCriado);

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

    public Chamado consultarDetalhesChamado(Long id) {
        ChamadoEntity chamadoEntity = chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        List<ChamadoSintomaEntity> sintomaEntities = chamadoSintomaRepository.findByChamadoId(id);

        Chamado model = chamadoMapper.toModel(chamadoEntity);
        model.setSintomas(chamadoSintomaMapper.toModelList(sintomaEntities));

        return model;
    }
}
