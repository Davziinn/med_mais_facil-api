package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import com.Unifor.MedMaisFacil.exceptions.MedicoNotFoundException;
import com.Unifor.MedMaisFacil.mapper.MedicoMapper;
import com.Unifor.MedMaisFacil.models.EspecialidadeMedico;
import com.Unifor.MedMaisFacil.models.Medico;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EspecialidadeService especialidadeService;

    public Medico salvarMedico (Medico medico) {
        if (medico == null) return null;

        Usuario usuarioEncontrado = usuarioService.buscarUsuarioById(medico.getUsuario().getId());

        EspecialidadeMedico especialidadeEncontrada = especialidadeService.buscarEspecialidadeById(medico.getEspecialidade().getId());


        if (usuarioEncontrado.getTipoUsuario() != TipoUsuario.MEDICO) {
            throw new RuntimeException("Usuário não é do tipo MEDICO");
        }

        medico = medico.toBuilder()
                .especialidade(especialidadeEncontrada)
                .usuario(usuarioEncontrado)
                .build();

        return medicoMapper.toModel(medicoRepository.save(medicoMapper.toEntity(medico)));
    }

    public Medico consultarMedicoById (Long id) {
        Medico medicoEncontrado = medicoMapper.toModel(medicoRepository.findById(id).orElseThrow(
                () -> new MedicoNotFoundException("Médico não encontrado")
        ));

        return medicoEncontrado;
    }
}
