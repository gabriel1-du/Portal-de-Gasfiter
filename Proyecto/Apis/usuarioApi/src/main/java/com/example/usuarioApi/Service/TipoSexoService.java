package com.example.usuarioApi.Service;

import java.util.List;

import com.example.usuarioApi.DTO.ClasesSexoTIpoDTO.SexoTipoDTO;
import com.example.usuarioApi.DTO.ClasesSexoTIpoDTO.leerSexoTipoDTO;
import com.example.usuarioApi.Model.SexoUsuario;

public interface TipoSexoService {

    List<leerSexoTipoDTO> leerTodosLosSexos();

    leerSexoTipoDTO leerSexoPorId(Integer id);

    SexoUsuario crearSexo(SexoTipoDTO sexoTipoDTO);

    SexoUsuario actualizarSexo(Integer id, SexoTipoDTO sexoTipoDT);

    void eliminarSexo(Integer id);

}
