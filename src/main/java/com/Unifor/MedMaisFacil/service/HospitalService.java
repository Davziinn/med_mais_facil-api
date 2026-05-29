package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.entity.HospitalEntity;
import com.Unifor.MedMaisFacil.enums.StatusChamado;
import com.Unifor.MedMaisFacil.enums.StatusHospital;
import com.Unifor.MedMaisFacil.exceptions.HospitalNotFoundException;
import com.Unifor.MedMaisFacil.mapper.*;
import com.Unifor.MedMaisFacil.models.*;
import com.Unifor.MedMaisFacil.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Auditable(acao = "Cadastrou um Hospital", modulo = "Hospitais")
    public Hospital salvarHospital (Hospital hospital) {
        if (hospital == null) return null;
        return hospitalMapper.toModel(hospitalRepository.save(hospitalMapper.toEntity(hospital)));
    }

    public Hospital buscarHospitalById(Long id) {
        HospitalEntity hospital = hospitalRepository.findById(id).orElseThrow(
                () -> new HospitalNotFoundException("Hospital não encontrado")
        );

        return hospitalMapper.toModel(hospital);
    }

    public List<Hospital> listarHospitais() {
        return hospitalRepository.findAll().stream()
                .map(hospitalMapper::toModel)
                .toList();
    }

    @Auditable(acao = "Atualizou um Hospital", modulo = "Hospitais")
    public Hospital atualizarHospital(Long id, Hospital novoDadosHospital) {
        Hospital hospitalEncontrado = buscarHospitalById(id);

        hospitalEncontrado = hospitalEncontrado.toBuilder()
                .nome(novoDadosHospital.getNome())
                .endereco(novoDadosHospital.getEndereco())
                .cnpj(novoDadosHospital.getCnpj())
                .cidade(novoDadosHospital.getCidade())
                .estado(novoDadosHospital.getEstado())
                .statusHospital(novoDadosHospital.getStatusHospital())
                .build();

        return hospitalMapper.toModel(hospitalRepository.save(hospitalMapper.toEntity(hospitalEncontrado)));
    }

    @Auditable(acao = "Deletou um Hospital", modulo = "Hospitais")
    public void deletarHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    public HospitalMetrica buscarMetricas() {
        long hospitaisAtivos = contarQuantidadeHospitaisByStatus(StatusHospital.ATIVO);
        long hospitaisLotados = contarQuantidadeHospitaisByStatus(StatusHospital.LOTADO);
        long hospitaisEmManutencao = contarQuantidadeHospitaisByStatus(StatusHospital.EM_MANUTENCAO);
        long hospitaisInativos = contarQuantidadeHospitaisByStatus(StatusHospital.INATIVO);

        long totalQuantidadeHospital = hospitaisAtivos + hospitaisLotados + hospitaisEmManutencao + hospitaisInativos;

        return new HospitalMetrica(totalQuantidadeHospital, hospitaisAtivos, hospitaisLotados, hospitaisEmManutencao);
    }

    public long contarQuantidadeHospitaisByStatus (StatusHospital status) {
        return hospitalRepository.countByStatusHospital(status);
    }
    public long contarQuantidadeHospitaisCadastrados () {
        return hospitalRepository.countHospitais();
    }
}
