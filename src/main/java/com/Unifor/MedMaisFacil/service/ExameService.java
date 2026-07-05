package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.entity.ExameEntity;
import com.Unifor.MedMaisFacil.exceptions.ExameNotFoundException;
import com.Unifor.MedMaisFacil.mapper.ExameMapper;
import com.Unifor.MedMaisFacil.models.Exame;
import com.Unifor.MedMaisFacil.repository.ExameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExameService {

    private final ExameRepository exameRepository;
    private final ExameMapper exameMapper;

    public Exame salvarExame (Exame dadosExame) {
        dadosExame = dadosExame.toBuilder().ativo(true).build();

        return exameMapper.toModel(exameRepository.save(exameMapper.toEntity(dadosExame)));
    }

    public List<Exame> buscarTodosExamesCadastrados () {
        return exameRepository.findAll().stream().map(exameMapper::toModel).toList();
    }

    public Exame buscarExameById (Long id) {
        return exameMapper.toModel(exameRepository.findById(id).orElseThrow(
                () -> new ExameNotFoundException("Exame não encontrado")
        ));
    }

    public Exame editarDadosExame (Long id, Exame novoDadosExame) {
        Exame exameEncontrado = buscarExameById(id);

        exameEncontrado = exameEncontrado.toBuilder()
                .nome(novoDadosExame.getNome() != null ? novoDadosExame.getNome() : exameEncontrado.getNome())
                .descricao(novoDadosExame.getDescricao() != null ? novoDadosExame.getDescricao() : exameEncontrado.getDescricao())
                .ativo(novoDadosExame.getAtivo() != null ? novoDadosExame.getAtivo() : exameEncontrado.getAtivo())
                .atualizadoEm(LocalDateTime.now())
                .build();

        return exameMapper.toModel(exameRepository.save(exameMapper.toEntity(exameEncontrado)));
    }

    public void deletarExameById (Long id) {
        boolean exameParaDeletar = existeExameById(id);
        if (exameParaDeletar) {
            exameRepository.deleteById(id);
        }
    }

    public boolean existeExameById (Long id) {
        if (!exameRepository.existsById(id)) {
            throw new ExameNotFoundException("Exame não existe");
        }

        return true;
    }

    public List<Exame> buscarTodosExamesAtivosCadastrados() {
        return exameRepository.findAll().stream().filter(ExameEntity::getAtivo).map(exameMapper::toModel).toList();
    }
}
