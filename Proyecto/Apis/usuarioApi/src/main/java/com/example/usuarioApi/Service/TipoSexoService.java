package com.example.usuarioApi.Service;

import java.util.List;

import com.example.usuarioApi.DTO.ClasesSexoTIpoDTO.SexoTipoDTO;
import com.example.usuarioApi.Model.SexoUsuario;

public interface TipoSexoService {

    List<SexoUsuario> leerTodasLasRegiones();

    SexoUsuario leerRegionPorId(Integer id);

    SexoUsuario crearRegion(SexoTipoDTO sexoTipoDTO);

    SexoUsuario actualizarRegion(Integer id, SexoTipoDTO sexoTipoDT);

    void eliminarRegion(Integer id);


}
