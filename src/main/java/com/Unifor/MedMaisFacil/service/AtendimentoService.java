package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.ChamadoEntity;
import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.exceptions.ChamadoNotAvailableException;
import com.Unifor.MedMaisFacil.exceptions.ChamadoNotFoundException;
import com.Unifor.MedMaisFacil.exceptions.MedicoNotFoundException;
import com.Unifor.MedMaisFacil.mapper.AtendimentoMapper;
import com.Unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.Unifor.MedMaisFacil.mapper.MedicoMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.Chamado;
import com.Unifor.MedMaisFacil.models.Medico;
import com.Unifor.MedMaisFacil.repository.AtendimentoRepository;
import com.Unifor.MedMaisFacil.repository.ChamadoRepository;
import com.Unifor.MedMaisFacil.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ChamadoMapper chamadoMapper;
    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    public Atendimento iniciar(Long chamadoId, Long medicoId) {

        ChamadoEntity chamadoEncontrado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new ChamadoNotFoundException("Chamado não encontrado"));

        MedicoEntity medicoEncontrado = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado"));

        if (!chamadoEncontrado.getStatusChamado().equals(StatusChamado.EM_ESPERA)) {
            throw new ChamadoNotAvailableException("Chamado não está disponível para atendimento");
        }

        if (existePorChamadoId(chamadoId)) {
            throw new RuntimeException("Já existe um atendimento para esse chamado");
        }

        Chamado chamado = chamadoMapper.toModel(chamadoEncontrado);
        Medico medico = medicoMapper.toModel(medicoEncontrado);

        Atendimento atendimentoCriado = Atendimento.builder()
                .chamado(chamado)
                .medico(medico)
                .dataInicio(LocalDateTime.now())
                .observacoes(null)
                .build();

        Atendimento atendimentoSalvo = atendimentoMapper.toModel(
                atendimentoRepository.save(atendimentoMapper.toEntity(atendimentoCriado))
        );
        chamadoEncontrado.setStatusChamado(StatusChamado.EM_ATENDIMENTO);
        chamadoRepository.save(chamadoEncontrado);

        return atendimentoSalvo;
    }

    public boolean existePorChamadoId (Long chamadoId) {
        return atendimentoRepository.existsByChamadoId(chamadoId);
    }


}
