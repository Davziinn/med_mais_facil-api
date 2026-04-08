package com.Unifor.MedMaisFacil.controllers;

import com.Unifor.MedMaisFacil.dtos.hospital.HospitalRequestDTO;
import com.Unifor.MedMaisFacil.dtos.hospital.HospitalResponseDTO;
import com.Unifor.MedMaisFacil.mapper.HospitalMapper;
import com.Unifor.MedMaisFacil.models.Hospital;
import com.Unifor.MedMaisFacil.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalMapper hospitalMapper;

    @PostMapping
    public ResponseEntity<HospitalResponseDTO> cadastrarHospital (@RequestBody HospitalRequestDTO hospitalRequestDTO) {
        Hospital hospitalCadastrado = hospitalService.salvarHospital(hospitalMapper.toModel(hospitalRequestDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalMapper.toDTO(hospitalCadastrado));
    }
}
