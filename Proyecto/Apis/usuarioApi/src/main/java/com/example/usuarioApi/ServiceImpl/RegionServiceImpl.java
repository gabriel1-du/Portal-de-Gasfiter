package com.example.usuarioApi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarioApi.DTO.clasesRegionDTO.leerRegionDTO;
import com.example.usuarioApi.DTO.clasesRegionDTO.RegionDTO;
import com.example.usuarioApi.Model.Region;
import com.example.usuarioApi.Repository.RegionRepository;
import com.example.usuarioApi.Service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {


    @Autowired
    private RegionRepository regionRepository;

    private leerRegionDTO mapToDto(Region region) {
        leerRegionDTO dto = new leerRegionDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setNombreRegion(region.getNombreRegion());
        return dto;
    }

    @Override
    public List<leerRegionDTO> leerTodasLasRegiones() {
        return regionRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public leerRegionDTO leerRegionPorId(Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id: " + id));
        return mapToDto(region);
    }

    @Override
    public Region crearRegion(RegionDTO regionDTO) {
        Region region = new Region();
        region.setNombreRegion(regionDTO.getNombreRegion());
        return regionRepository.save(region);
    }

    @Override
    public Region actualizarRegion(Integer id, RegionDTO regionDTO) {
        Region regionExistente = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id: " + id));
        
        regionExistente.setNombreRegion(regionDTO.getNombreRegion());
        return regionRepository.save(regionExistente);
    }

    @Override
    public void eliminarRegion(Integer id) {
        Region regionExistente = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada con id: " + id));
        regionRepository.delete(regionExistente);
    }


}
