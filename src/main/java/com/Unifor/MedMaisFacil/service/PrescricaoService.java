package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.entity.PrescricaoEntity;
import com.Unifor.MedMaisFacil.mapper.PrescricaoMapper;
import com.Unifor.MedMaisFacil.models.Atendimento;
import com.Unifor.MedMaisFacil.models.Prescricao;
import com.Unifor.MedMaisFacil.repository.PrescricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescricaoService {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Autowired
    private PrescricaoMapper prescricaoMapper;

    @Autowired
    private AtendimentoService atendimentoService;

    @Auditable(acao = "Salvou uma prescrição", modulo = "Prescrições")
    public Prescricao salvar(Prescricao prescricao, Long atendimentoId) {
        Atendimento atendimentoEncontrado = atendimentoService.buscarAtendimentoById(atendimentoId);

        Optional<PrescricaoEntity> prescricaoExistente = prescricaoRepository.findByAtendimentoId(atendimentoId);


        Prescricao prescricaoParaSalvar;

        if (prescricaoExistente.isPresent()) {
            Prescricao prescricaoAtual = prescricaoMapper.toModel(prescricaoExistente.get());

            prescricaoParaSalvar = prescricaoAtual.toBuilder()
                    .orientacoes(prescricao.getOrientacoes())
                    .retornoConsulta(prescricao.getRetornoConsulta())
                    .exames(prescricao.getExames())
                    .atendimento(atendimentoEncontrado)
                    .medicamentos(prescricao.getMedicamentos())
                    .build();
        } else {
            prescricaoParaSalvar = prescricao.toBuilder()
                    .atendimento(atendimentoEncontrado)
                    .build();
        }

        return prescricaoMapper.toModel(prescricaoRepository.save(prescricaoMapper.toEntity(prescricaoParaSalvar)));
    }

    public Map<Long, Optional<Prescricao>> buscarPrescricoesIndexAtendimento (List<Long> atendimentoIds) {

        return prescricaoRepository
                .findByAtendimentoIdIn(atendimentoIds)
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getAtendimento().getId(),
                        p -> Optional.of(prescricaoMapper.toModel(p))
                ));
    }
}
