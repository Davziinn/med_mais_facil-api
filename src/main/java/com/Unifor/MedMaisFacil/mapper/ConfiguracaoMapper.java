package com.Unifor.MedMaisFacil.mapper;

import com.Unifor.MedMaisFacil.dtos.configuracao.ConfiguracaoRequestDTO;
import com.Unifor.MedMaisFacil.dtos.configuracao.ConfiguracaoResponseDTO;
import com.Unifor.MedMaisFacil.entity.ConfiguracaoEntity;
import com.Unifor.MedMaisFacil.models.Configuracao;

public interface ConfiguracaoMapper {

    Configuracao toModel (ConfiguracaoEntity entity);

    ConfiguracaoEntity toEntity (Configuracao model);

    Configuracao toModel (ConfiguracaoRequestDTO dto);

    ConfiguracaoResponseDTO toDTO (Configuracao model);
}
