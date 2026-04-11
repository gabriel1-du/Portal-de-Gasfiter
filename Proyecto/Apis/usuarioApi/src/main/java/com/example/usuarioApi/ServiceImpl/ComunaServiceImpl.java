package com.example.usuarioApi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarioApi.DTO.ClasesComunaDTO.ActualizarNombreComunaDTO;
import com.example.usuarioApi.DTO.ClasesComunaDTO.ComunaDTO;
import com.example.usuarioApi.DTO.ClasesComunaDTO.leerComunaDTO;
import com.example.usuarioApi.Model.Comuna;
import com.example.usuarioApi.Model.Region;
import com.example.usuarioApi.Repository.ComunaRepository;
import com.example.usuarioApi.Repository.RegionRepository;
import com.example.usuarioApi.Service.ComunaService;

@Service
public class ComunaServiceImpl implements ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    private leerComunaDTO mapToDto(Comuna comuna) {
        leerComunaDTO dto = new leerComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        if (comuna.getRegion() != null) {
            dto.setIdRegion(comuna.getRegion().getIdRegion());
            dto.setNombreRegion(comuna.getRegion().getNombreRegion());
        }
        return dto;
    }

    @Override
    public List<leerComunaDTO> leerTodasLasComunas() {
        return comunaRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public leerComunaDTO leerComunaPorId(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id: " + id));
        return mapToDto(comuna);
    }

    @Override
    public Comuna crearComuna(ComunaDTO comunaDTO) {
        Region region = regionRepository.findById(comunaDTO.getIdRegion())
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id: " + comunaDTO.getIdRegion()));
        Comuna comuna = new Comuna();
        comuna.setNombreComuna(comunaDTO.getNombreComuna());
        comuna.setRegion(region);
        return comunaRepository.save(comuna);
    }

    @Override
    public Comuna actualizarComuna(Integer id, ComunaDTO comunaDTO) {
        Comuna comunaExistente = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id: " + id));
        Region region = regionRepository.findById(comunaDTO.getIdRegion())
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id: " + comunaDTO.getIdRegion()));
        comunaExistente.setNombreComuna(comunaDTO.getNombreComuna());
        comunaExistente.setRegion(region);
        return comunaRepository.save(comunaExistente);
    }

    @Override
    public void eliminarComuna(Integer id) {
        Comuna comunaExistente = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id: " + id));
        comunaRepository.delete(comunaExistente);
    }

    @Override
    public Comuna actualizarNombreComuna(Integer id, ActualizarNombreComunaDTO comunaDTO) {
        Comuna comunaExistente = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id: " + id));

        if (comunaDTO.getNombreComuna() != null && !comunaDTO.getNombreComuna().trim().isEmpty()) {
            comunaExistente.setNombreComuna(comunaDTO.getNombreComuna());
        }
        return comunaRepository.save(comunaExistente);
    }
}
