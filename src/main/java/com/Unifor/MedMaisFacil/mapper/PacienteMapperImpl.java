package com.Unifor.MedMaisFacil.mapper;


import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.models.Paciente;
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
                model.getAtualizadoEm()
        );
    }
}
