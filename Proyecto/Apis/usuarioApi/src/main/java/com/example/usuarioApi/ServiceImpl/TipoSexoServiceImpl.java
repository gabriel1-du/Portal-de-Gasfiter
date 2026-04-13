package com.example.usuarioApi.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarioApi.DTO.ClasesSexoTIpoDTO.SexoTipoDTO;
import com.example.usuarioApi.DTO.ClasesSexoTIpoDTO.leerSexoTipoDTO;
import com.example.usuarioApi.Model.SexoUsuario;
import com.example.usuarioApi.Repository.SexoUsuarioRepository;
import com.example.usuarioApi.Service.TipoSexoService;

@Service
public class TipoSexoServiceImpl implements TipoSexoService {

    @Autowired
    private SexoUsuarioRepository sexoUsuarioRepository;

    private leerSexoTipoDTO mapToDto(SexoUsuario sexo) {
        leerSexoTipoDTO dto = new leerSexoTipoDTO();
        dto.setIdSexo(sexo.getIdSexo());
        dto.setNombreSexo(sexo.getNombreSexo());
        return dto;
    }

    @Override
    public List<leerSexoTipoDTO> leerTodosLosSexos() {
        return sexoUsuarioRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public leerSexoTipoDTO leerSexoPorId(Integer id) {
        SexoUsuario sexo = sexoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sexo no encontrado con id: " + id));
        return mapToDto(sexo);
    }

    @Override
    public SexoUsuario crearSexo(SexoTipoDTO sexoTipoDTO) {
        SexoUsuario sexoUsuario = new SexoUsuario();
        sexoUsuario.setNombreSexo(sexoTipoDTO.getNombreSexo());
        return sexoUsuarioRepository.save(sexoUsuario);
    }

    @Override
    public SexoUsuario actualizarSexo(Integer id, SexoTipoDTO sexoTipoDT) {
        SexoUsuario sexoExistente = sexoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sexo no encontrado con id: " + id));
        sexoExistente.setNombreSexo(sexoTipoDT.getNombreSexo());
        return sexoUsuarioRepository.save(sexoExistente);
    }

    @Override
    public void eliminarSexo(Integer id) {
        SexoUsuario sexoExistente = sexoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sexo no encontrado con id: " + id));
        sexoUsuarioRepository.delete(sexoExistente);
    }
}