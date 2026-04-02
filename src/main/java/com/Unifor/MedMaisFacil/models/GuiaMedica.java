package com.Unifor.MedMaisFacil.models;

import com.Unifor.MedMaisFacil.enums.Convenio;
import com.Unifor.MedMaisFacil.enums.StatusGuiaMedica;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GuiaMedica {

    private Long id;
    private StatusGuiaMedica statusGuiaMedica;
    private String numeroGuia;
    private LocalDateTime dataSolicitacao;
    private Convenio convenio;
    private String observacoes;
    private Atendimento atendimento;

    public void validarDataSolicitacao (LocalDateTime dataNova) {
        if (this.dataSolicitacao.isBefore(dataNova)) {
            throw new IllegalArgumentException("Data da solicitação é inválida");
        }
    }

    public void autorizarGuia(){
        if(this.statusGuiaMedica == StatusGuiaMedica.NEGADA){
            throw new IllegalStateException("Nao e possivel autorizar a guia ja negada");
        }

        if(this.statusGuiaMedica == StatusGuiaMedica.AUTORIZADA){
            throw new IllegalStateException("Guia ja autorizada");
        }
        this.statusGuiaMedica = StatusGuiaMedica.AUTORIZADA;
    }

    public void cancelarGuia() {
        if (this.statusGuiaMedica == StatusGuiaMedica.NEGADA) {
            throw new IllegalStateException("Guia já esta negada");
        }

        if (this.statusGuiaMedica == StatusGuiaMedica.AUTORIZADA) {
            throw new IllegalStateException("Nao e possível negar uma guia ja autorizada");
        }

        this.statusGuiaMedica = StatusGuiaMedica.NEGADA;
    }
}
