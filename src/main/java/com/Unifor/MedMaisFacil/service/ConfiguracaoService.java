package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.mapper.ConfiguracaoMapper;
import com.Unifor.MedMaisFacil.models.Configuracao;
import com.Unifor.MedMaisFacil.repository.ConfiguracaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfiguracaoService {

    private final ConfiguracaoRepository configuracaoRepository;
    private final ConfiguracaoMapper configuracaoMapper;

    private static final Long CONFIG_ID_DEFAULT = 1L;
    private static final String CONFIG_STATUS_DEFAULT = "Operando normalmente";
    private static final String CONFIG_MENSAGEM_DEFAULT = "Bem-vindo ao Med+Fácil. Acompanhe sua senha em tempo real.";

    public Configuracao buscar () {
        Configuracao configuracaoBuscada = configuracaoMapper.toModel(configuracaoRepository.findById(CONFIG_ID_DEFAULT).orElse(configuracaoMapper.toEntity(configuracaoDefault())));

        return configuracaoBuscada;
    }

    @Auditable(acao = "Alterou as configurações", modulo = "Configurações")
    public Configuracao salvar (Configuracao configuracao) {
        configuracao.setId(CONFIG_ID_DEFAULT);

        return configuracaoMapper.toModel(configuracaoRepository.save(configuracaoMapper.toEntity(configuracao)));
    }

    private Configuracao configuracaoDefault () {
        Configuracao configuracao = Configuracao.builder()
                .id(CONFIG_ID_DEFAULT)
                .tempoLimiteChamado(45)
                .quantidadeMaximaFila(80)
                .chamadaAutomatica(true)
                .statusGeral(CONFIG_STATUS_DEFAULT)
                .mensagemPaciente(CONFIG_MENSAGEM_DEFAULT)
                .notificacoesPush(false)
                .build();

        return configuracao;

    }
}
