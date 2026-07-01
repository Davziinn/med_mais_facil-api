package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.mapper.GuiaExameMapper;
import com.Unifor.MedMaisFacil.models.GuiaExame;
import com.Unifor.MedMaisFacil.repository.GuiaExameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuiaExameSerivce {

    private final GuiaExameRepository guiaExameRepository;
    private final GuiaExameMapper guiaExameMapper;

    @Transactional
    public void salvarGuiaExame (List<GuiaExame> guiaExames) {
        guiaExameRepository.saveAll(guiaExames.stream().map(guiaExameMapper::toEntity).toList()).stream().map(guiaExameMapper::toModel).toList();
        guiaExameRepository.flush();
    }

    @Transactional
    public void deletarTudoPeloGuiaId(Long id) {
        guiaExameRepository.deleteAllByGuiaId(id);
        guiaExameRepository.flush();
    }
}
