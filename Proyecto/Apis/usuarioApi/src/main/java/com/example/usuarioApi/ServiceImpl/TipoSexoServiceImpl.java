package com.example.usuarioApi.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarioApi.DTO.ClasesSexoTIpoDTO.SexoTipoDTO;
import com.example.usuarioApi.Model.SexoUsuario;
import com.example.usuarioApi.Repository.SexoUsuarioRepository;
import com.example.usuarioApi.Service.TipoSexoService;

@Service
public class TipoSexoServiceImpl implements TipoSexoService {

    @Autowired
    private SexoUsuarioRepository sexoUsuarioRepository;

    @Override
    public List<SexoUsuario> leerTodasLasRegiones() {
        return sexoUsuarioRepository.findAll();
    }

    @Override
    public SexoUsuario leerRegionPorId(Integer id) {
        return sexoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sexo no encontrado con id: " + id));
    }

    @Override
    public SexoUsuario crearRegion(SexoTipoDTO sexoTipoDTO) {
        SexoUsuario sexoUsuario = new SexoUsuario();
        sexoUsuario.setNombreSexo(sexoTipoDTO.getNombreSexo());
        return sexoUsuarioRepository.save(sexoUsuario);
    }

    @Override
    public SexoUsuario actualizarRegion(Integer id, SexoTipoDTO sexoTipoDT) {
        SexoUsuario sexoExistente = sexoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sexo no encontrado con id: " + id));
        sexoExistente.setNombreSexo(sexoTipoDT.getNombreSexo());
        return sexoUsuarioRepository.save(sexoExistente);
    }

    @Override
    public void eliminarRegion(Integer id) {
        SexoUsuario sexoExistente = sexoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sexo no encontrado con id: " + id));
        sexoUsuarioRepository.delete(sexoExistente);
    }
}