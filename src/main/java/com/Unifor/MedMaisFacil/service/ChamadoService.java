package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ChamadoMapper chamadoMapper;

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

    public Chamado abrirChamado (Chamado chamado, List<Sintoma> sintomas) {

        // 1. Buscar um paciente
        Paciente pacienteEncontrato = pacienteService.buscarPacienteById(chamado.getPaciente().getId());
        // 2. Buscar um hospital (Por enquanto, vai buscar por ID, depois altero para buscar o mais próximo do paciente)
        Hospital hospitalEncontrado = hospitalService.buscarHospitalById(chamado.getHospital().getId());
        // 3. Criar um chamado
        Chamado chamadoCriado = registrarChamado(chamado, pacienteEncontrato, hospitalEncontrado);
        // 4. Salvar os sintomas
        chamadoSintomaService.salvarSintomas(chamadoCriado, sintomas);
        // 5. Classificar prioridade
        triagemService.calcularPrioridade(chamado);
        // 6. Gerar senha / Fila
        filaService.gerarSenha(chamado);

        return chamadoCriado;
    }

    public Chamado registrarChamado(Chamado chamado, Paciente paciente, Hospital hospital) {
        Chamado vinculaAoChamado = chamado.toBuilder()
                .paciente(paciente)
                .hospital(hospital)
                .build();

        return chamadoMapper.toModel(chamadoRepository.save(chamadoMapper.toEntity(vinculaAoChamado)));
    }
}
