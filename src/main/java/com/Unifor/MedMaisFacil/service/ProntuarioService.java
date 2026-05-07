package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final AtendimentoRepository atendimentoRepository;
}
