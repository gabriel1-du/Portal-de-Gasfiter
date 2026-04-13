package com.example.usuarioApi.Service;

import com.example.usuarioApi.DTO.ClasesTipoUsuariosDTO.crearTipoUsuarioDTO;
import com.example.usuarioApi.DTO.ClasesTipoUsuariosDTO.leerTipoUsuarioDTO;
import com.example.usuarioApi.Model.TipoUsuario;

import java.util.List;

public interface TipoUsuarioService {

    List<leerTipoUsuarioDTO> leerTodosLosTiposUsuario();

    leerTipoUsuarioDTO leerTipoUsuarioPorId(Integer id);

    TipoUsuario crearTipoUsuario(crearTipoUsuarioDTO tipoUsuarioDTO);

    TipoUsuario actualizarTipoUsuario(Integer id, crearTipoUsuarioDTO tipoUsuarioDTO);

    void eliminarTipoUsuario(Integer id);
}