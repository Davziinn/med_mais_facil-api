package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.dtos.prontuario.ProntuarioPacienteResponseDTO;
import com.Unifor.MedMaisFacil.mapper.ProntuarioMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.Paciente;
import com.Unifor.MedMaisFacil.models.Prescricao;
import com.Unifor.MedMaisFacil.models.Prontuario;
import com.Unifor.MedMaisFacil.repository.AtendimentoRepository;
import com.Unifor.MedMaisFacil.repository.PacienteRepository;
import com.Unifor.MedMaisFacil.repository.PrescricaoRepository;
import com.Unifor.MedMaisFacil.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final PacienteRepository pacienteRepository;
    private final PacienteService pacienteService;

    private final AtendimentoRepository atendimentoRepository;
    private final AtendimentoService atendimentoService;

    private final PrescricaoRepository prescricaoRepository;
    private final PrescricaoService prescricaoService;

    private final ProntuarioRepository prontuarioRepository;
    private final ProntuarioMapper prontuarioMapper;

    public ProntuarioPacienteResponseDTO buscarProntuario (Long pacienteId) {

        Paciente pacienteEncontrado = pacienteService.buscarPacienteById(pacienteId);

        List<Atendimento> atendimentosEncontradosByPaciente = atendimentoService.buscarAtendimentosByPacienteId(pacienteId);

        List<Long> idsExtraidos = atendimentosEncontradosByPaciente.stream()
                .map(Atendimento::getId)
                .toList();

        Map<Long, Optional<Prescricao>> prescricoesIndexadas = prescricaoService.buscarPrescricoesIndexAtendimento(idsExtraidos);

        Map<Long, Optional<Prontuario>> prontuariosIndexados = buscaProntuariosIndexAtendimento(idsExtraidos);

        idsExtraidos.forEach(id -> {
            prescricoesIndexadas.putIfAbsent(id, Optional.empty());
            prontuariosIndexados.putIfAbsent(id, Optional.empty());
        });

        return prontuarioMapper.toDTO(pacienteEncontrado, atendimentosEncontradosByPaciente, prescricoesIndexadas, prontuariosIndexados);
    }

    public Map<Long, Optional<Prontuario>> buscaProntuariosIndexAtendimento (List<Long> atendimentoIds) {
        return prontuarioRepository
                .findByAtendimentoIdIn(atendimentoIds)
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getAtendimento().getId(),
                        p -> Optional.of(prontuarioMapper.toModel(p))
                ));

    }
}
