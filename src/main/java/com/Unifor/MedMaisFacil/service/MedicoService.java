package com.Unifor.MedMaisFacil.service;

import com.Unifor.MedMaisFacil.annotation.Auditable;
import com.Unifor.MedMaisFacil.dtos.medico.MedicoUpdateRequestDTO;
import com.Unifor.MedMaisFacil.entity.MedicoEntity;
import com.Unifor.MedMaisFacil.enums.TipoUsuario;
import com.Unifor.MedMaisFacil.exceptions.MedicoNotFoundException;
import com.Unifor.MedMaisFacil.mapper.MedicoMapper;
import com.Unifor.MedMaisFacil.models.EspecialidadeMedico;
import com.Unifor.MedMaisFacil.models.Medico;
import com.Unifor.MedMaisFacil.models.Usuario;
import com.Unifor.MedMaisFacil.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Auditable(acao = "Cadastrou um médico", modulo = "Medicos")
    public Medico salvarMedico (Medico medico) {
        if (medico == null) return null;

        Usuario usuarioEncontrado = usuarioService.buscarUsuarioById(medico.getUsuario().getId());

        EspecialidadeMedico especialidadeEncontrada = especialidadeService.buscarEspecialidadeByNome(medico.getEspecialidade().getNome());


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

    public long contarQuantidadeMedicoPorHospital (Long hospitalId) {
        return medicoRepository.countMedicoByHospital(hospitalId);
    }

    public long contarQuantidadeMedicosCadastrados () {
        return medicoRepository.countMedicosAtivos();
    }

    public Medico buscarPorUsuarioId(Long usuarioId) {
        return medicoMapper.toModel(medicoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado")));
    }

    public Medico atualizarMedico(Long id, MedicoUpdateRequestDTO dto) {
        Medico medico = medicoMapper.toModel(medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado")));
        medico.setCrm(dto.crm());
        medico.setSexo(dto.sexo());
        medico.setDataNascimento(dto.dataNascimento());
        return medicoMapper.toModel(medicoRepository.save(medicoMapper.toEntity(medico)));
    }

    public Long buscarMedicoIdByEmail (String email) {
        return medicoRepository.findByUsuarioEmail(email)
                .map(MedicoEntity::getId)
                .orElse(null);
    }

    public Medico buscarMedicoLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOuEmail = authentication.getName();

        Usuario usuario = usuarioService.buscarUsuarioByEmail(usernameOuEmail);

        return buscarPorUsuarioId(usuario.getId());
    }
}
