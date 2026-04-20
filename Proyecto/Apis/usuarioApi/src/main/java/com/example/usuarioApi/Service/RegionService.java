package com.example.usuarioApi.Service;

import java.util.List;

import com.example.usuarioApi.DTO.clasesRegionDTO.leerRegionDTO;
import com.example.usuarioApi.DTO.clasesRegionDTO.RegionDTO;
import com.example.usuarioApi.Model.Region;

public interface RegionService {

    List<leerRegionDTO> leerTodasLasRegiones();

    leerRegionDTO leerRegionPorId(Integer id);

    Region crearRegion(RegionDTO regionDTO);

    Region actualizarRegion(Integer id, RegionDTO regionDTO);

    void eliminarRegion(Integer id);

}
