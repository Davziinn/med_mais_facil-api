package com.Unifor.MedMaisFacil.mapper;


import com.Unifor.MedMaisFacil.dtos.paciente.PacienteRequestDTO;
import com.Unifor.MedMaisFacil.dtos.paciente.PacienteResponseDTO;
import com.Unifor.MedMaisFacil.entity.PacienteEntity;
import com.Unifor.MedMaisFacil.models.Paciente;
import com.Unifor.MedMaisFacil.utils.CalcularIdadeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapperImpl implements PacienteMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public Paciente toModel(PacienteEntity entity) {

        Paciente pacienteModel = new Paciente();

        pacienteModel.setId(entity.getId());
        pacienteModel.setNome(entity.getNome());
        pacienteModel.setCpf(entity.getCpf());
        pacienteModel.setTelefone(entity.getTelefone());
        pacienteModel.setSexo(entity.getSexo());
        pacienteModel.setCriadoEm(entity.getCriadoEm());
        pacienteModel.setAtualizadoEm(entity.getAtualizadoEm());
        pacienteModel.setConvenio(entity.getConvenio());
        pacienteModel.setDataNascimento(entity.getDataNascimento());
        pacienteModel.setCondicoesPreexistentes(entity.getCondicoesPreexistentes());
        pacienteModel.setUsuario(entity.getUsuario() != null ? usuarioMapper.toModel(entity.getUsuario()) : null);

        return pacienteModel;
    }

    @Override
    public PacienteEntity toEntity(Paciente model) {
        return new PacienteEntity(
                model.getId(),
                model.getNome(),
                model.getCpf(),
                model.getTelefone(),
                model.getDataNascimento(),
                model.getSexo(),
                model.getCriadoEm(),
                model.getAtualizadoEm(),
                model.getConvenio(),
                model.getCondicoesPreexistentes(),
                model.getUsuario() != null ? usuarioMapper.toEntity(model.getUsuario()) : null
        );
    }

    @Override
    public Paciente toModel(PacienteRequestDTO dto) {
        return Paciente.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .email(dto.email())
                .senha(dto.senha())
                .dataNascimento(dto.dataNascimento())
                .sexo(dto.sexo())
                .condicoesPreexistentes(dto.condicoesPreexistentes())
                .convenio(dto.convenio())
                .build();
    }

    @Override
    public PacienteResponseDTO toDTO(Paciente model) {
        return new PacienteResponseDTO(
                model.getId(),
                model.getNome(),
                model.getCpf(),
                model.getTelefone(),
                CalcularIdadeUtils.calcular(model.getDataNascimento()),
                model.getSexo(),
                model.getConvenio(),
                model.getCondicoesPreexistentes()
        );
    }
}
