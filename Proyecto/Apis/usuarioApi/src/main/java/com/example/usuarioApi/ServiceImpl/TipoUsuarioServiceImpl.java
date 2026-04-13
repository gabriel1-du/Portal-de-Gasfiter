package com.example.usuarioApi.ServiceImpl;

import com.example.usuarioApi.DTO.ClasesTipoUsuariosDTO.crearTipoUsuarioDTO;
import com.example.usuarioApi.DTO.ClasesTipoUsuariosDTO.leerTipoUsuarioDTO;
import com.example.usuarioApi.Model.TipoUsuario;
import com.example.usuarioApi.Repository.TipoUsuarioRepository;
import com.example.usuarioApi.Service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    private leerTipoUsuarioDTO mapToDto(TipoUsuario tipoUsuario) {
        leerTipoUsuarioDTO dto = new leerTipoUsuarioDTO();
        dto.setIdTipoUsuario(tipoUsuario.getIdTipoUsuario());
        dto.setNombreRol(tipoUsuario.getNombreRol());
        return dto;
    }

    @Override
    public List<leerTipoUsuarioDTO> leerTodosLosTiposUsuario() {
        return tipoUsuarioRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public leerTipoUsuarioDTO leerTipoUsuarioPorId(Integer id) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de Usuario no encontrado con id: " + id));
        return mapToDto(tipoUsuario);
    }

    @Override
    public TipoUsuario crearTipoUsuario(crearTipoUsuarioDTO tipoUsuarioDTO) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNombreRol(tipoUsuarioDTO.getNombreRol());
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    @Override
    public TipoUsuario actualizarTipoUsuario(Integer id, crearTipoUsuarioDTO tipoUsuarioDTO) {
        TipoUsuario tipoUsuarioExistente = tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de Usuario no encontrado con id: " + id));
        tipoUsuarioExistente.setNombreRol(tipoUsuarioDTO.getNombreRol());
        return tipoUsuarioRepository.save(tipoUsuarioExistente);
    }

    @Override
    public void eliminarTipoUsuario(Integer id) {
        TipoUsuario tipoUsuarioExistente = tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de Usuario no encontrado con id: " + id));
        tipoUsuarioRepository.delete(tipoUsuarioExistente);
    }
}