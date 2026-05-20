package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.exceptions.RegraNegocioException;
import com.Unifor.MedMaisFacil.exceptions.SintomaNotFoundException;
import com.Unifor.MedMaisFacil.mapper.SintomaMapper;
import com.Unifor.MedMaisFacil.models.Sintoma;
import com.Unifor.MedMaisFacil.repository.SintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SintomaService {

    @Autowired
    private SintomaRepository sintomaRepository;

    @Autowired
    private SintomaMapper sintomaMapper;

    public Sintoma salvarSintoma (Sintoma sintoma) {
        if (sintoma == null) return null;
        return sintomaMapper.toModel(sintomaRepository.save(sintomaMapper.toEntity(sintoma)));
    }

    public List<Sintoma> listarSintomasCadastrados() {
        List<Sintoma> sintomasEncontrados = sintomaRepository.findAll().stream().map(sintomaMapper::toModel).toList();

        return sintomasEncontrados;
    }

    public Sintoma buscarSintomaById (Long id) {
        if (Objects.isNull(id)) return null;

        return sintomaMapper.toModel(sintomaRepository.findById(id).orElseThrow(
                () -> new SintomaNotFoundException("Sintoma não encontrado")
        ));
    }

    public Sintoma atualizarSintoma(Long id, Sintoma sintoma) {
        Sintoma sintomaEncontrado = buscarSintomaById(id);

        sintomaEncontrado = sintomaEncontrado.toBuilder()
                .descricao(sintoma.getDescricao())
                .prioridadeSintoma(sintoma.getPrioridadeSintoma())
                .ativo(sintoma.isAtivo())
                .build();

        return sintomaEncontrado;
    }

    public void deletarSintoma(Long id) {
        sintomaRepository.deleteById(id);
    }
}
