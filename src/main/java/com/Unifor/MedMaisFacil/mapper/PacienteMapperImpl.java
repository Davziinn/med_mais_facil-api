package com.Unifor.MedMaisFacil.mapper;


import com.Unifor.MedMaisFacil.dtos.paciente.PacienteRequestDTO;
import com.Unifor.MedMaisFacil.dtos.paciente.PacienteResponseDTO;
import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.models.Paciente;
import com.Unifor.MedMaisFacil.utils.CalcularIdadeUtils;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public Paciente toModel(PacienteEntity entity) {

        Paciente pacienteModel = new Paciente();

        pacienteModel.setId(entity.getId());
        pacienteModel.setNome(entity.getNome());
        pacienteModel.setCpf(entity.getCpf());
        pacienteModel.setSexo(entity.getSexo());
        pacienteModel.setCriadoEm(entity.getCriadoEm());
        pacienteModel.setAtualizadoEm(entity.getAtualizadoEm());
        pacienteModel.setDataNascimento(entity.getDataNascimento());
        pacienteModel.setCondicoesPreexistentes(entity.getCondicoesPreexistentes());

        return pacienteModel;
    }

    @Override
    public PacienteEntity toEntity(Paciente model) {
        return new PacienteEntity(
                model.getId(),
                model.getNome(),
                model.getCpf(),
                model.getDataNascimento(),
                model.getSexo(),
                model.getCriadoEm(),
                model.getAtualizadoEm(),
                model.getCondicoesPreexistentes()
        );
    }

    @Override
    public Paciente toModel(PacienteRequestDTO dto) {
        return Paciente.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .dataNascimento(dto.dataNascimento())
                .sexo(dto.sexo())
                .condicoesPreexistentes(dto.condicoesPreexistentes())
                .build();
    }

    @Override
    public PacienteResponseDTO toDTO(Paciente model) {
        return new PacienteResponseDTO(
                model.getId(),
                model.getNome(),
                model.getCpf(),
                CalcularIdadeUtils.calcular(model.getDataNascimento()),
                model.getSexo(),
                model.getCondicoesPreexistentes()
        );
    }
}
