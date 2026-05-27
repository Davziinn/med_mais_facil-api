package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.exceptions.EspecialidadeMedicoNotFoundException;
import com.Unifor.MedMaisFacil.mapper.EspecialidadeMapper;
import com.Unifor.MedMaisFacil.models.EspecialidadeMedico;
import com.Unifor.MedMaisFacil.repository.EspecialidadeMedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EspecialidadeService {

    private final EspecialidadeMedicoRepository especialidadeMedicoRepository;

    private final EspecialidadeMapper especialidadeMapper;

    @Auditable(acao = "Cadastrou uma especialidade", modulo = "Especialidades")
    public EspecialidadeMedico salvarEspecialidade (EspecialidadeMedico especialidade) {
        if (Objects.isNull(especialidade)) return null;

        return especialidadeMapper.toModel(especialidadeMedicoRepository.save(especialidadeMapper.toEntity(especialidade)));
    }

    public EspecialidadeMedico buscarEspecialidadeById (Long id) {
        return especialidadeMapper.toModel(especialidadeMedicoRepository.findById(id).orElseThrow(
                () -> new EspecialidadeMedicoNotFoundException("Especialidade não encontrada")
        ));
    }

    public List<EspecialidadeMedico> listarEspecialidades () {
        return especialidadeMedicoRepository.findAll().stream()
                .map(especialidadeMapper::toModel)
                .toList();
    }

    @Auditable(acao = "Atualizou uma especialidade", modulo = "Especialidades")
    public EspecialidadeMedico atualizarDadosEspecialidade(Long id, EspecialidadeMedico especialidadeAtualizado) {
        EspecialidadeMedico especialidadeEncontrado = buscarEspecialidadeById(id);

        especialidadeEncontrado = especialidadeEncontrado.toBuilder()
                .nome(especialidadeAtualizado.getNome())
                .descricao(especialidadeAtualizado.getDescricao())
                .build();

        return especialidadeMapper.toModel(especialidadeMedicoRepository.save(especialidadeMapper.toEntity(especialidadeEncontrado)));
    }

    @Auditable(acao = "Deletou uma especialidade", modulo = "Especialidades")
    public void deletarEspecialidadeById(Long id) {
        especialidadeMedicoRepository.deleteById(id);
    }

    public EspecialidadeMedico buscarEspecialidadeByNome (String nomeEspecialidade) {
        EspecialidadeMedico nomeEspecialidadeMedicoEncontrado = especialidadeMapper.toModel(especialidadeMedicoRepository.findByNome(nomeEspecialidade).orElseThrow(
                () -> new EspecialidadeMedicoNotFoundException("Especialidade Medica não encontrada")
        ));

        return nomeEspecialidadeMedicoEncontrado;
    }
}
