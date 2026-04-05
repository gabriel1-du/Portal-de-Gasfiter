package com.example.usuarioApi.Service;

import com.example.usuarioApi.DTO.clasesUsuarioDTO.crearUsuarioDTO;
import com.example.usuarioApi.DTO.clasesUsuarioDTO.eliminarUserDTO;
import com.example.usuarioApi.DTO.clasesUsuarioDTO.leerUsuarioDTO;
import com.example.usuarioApi.DTO.clasesUsuarioDTO.actualizarUserDTO;
import com.example.usuarioApi.DTO.clasesUsuarioDTO.actualizarUsuarioDTOAdmin;

public interface UsuarioService {

    leerUsuarioDTO crearUsuario(crearUsuarioDTO usuarioDTO);

    leerUsuarioDTO leerUsuario(Integer id); // Método para leer un usuario por su ID (Get)

    leerUsuarioDTO actualizarUsuario(Integer id, actualizarUserDTO usuarioDTO);

    void eliminarUsuario(Integer id);

    void eliminarUsuarioConIngresoContraseña(Integer id, eliminarUserDTO deleteDTO); // Método para eliminar un usuario con confirmación de contraseña

    leerUsuarioDTO actualizarUsuarioAdmin(Integer id, actualizarUsuarioDTOAdmin usuarioDTO);

}
