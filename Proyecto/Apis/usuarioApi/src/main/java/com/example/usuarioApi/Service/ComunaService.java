package com.example.usuarioApi.Service;



import java.util.List;

import com.example.usuarioApi.DTO.ClasesComunaDTO.ActualizarNombreComunaDTO;
import com.example.usuarioApi.DTO.ClasesComunaDTO.ComunaDTO;
import com.example.usuarioApi.DTO.ClasesComunaDTO.leerComunaDTO;
import com.example.usuarioApi.Model.Comuna;

public interface ComunaService {

    List<leerComunaDTO> leerTodasLasComunas();

    leerComunaDTO leerComunaPorId(Integer id);

    Comuna crearComuna(ComunaDTO comunaDTO);

    Comuna actualizarComuna(Integer id, ComunaDTO comunaDTO);

    void eliminarComuna(Integer id);

    Comuna actualizarNombreComuna(Integer id, ActualizarNombreComunaDTO comunaDTO);
}
