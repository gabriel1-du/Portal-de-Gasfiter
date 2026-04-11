package com.example.usuarioApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarioApi.DTO.ClasesSexoTIpoDTO.SexoTipoDTO;
import com.example.usuarioApi.Model.SexoUsuario;
import com.example.usuarioApi.Service.TipoSexoService;

@RestController
@RequestMapping("api/sexosApi")
public class SexoUsuarioController {

    @Autowired
    private TipoSexoService tipoSexoService;

    @GetMapping
    public ResponseEntity<List<SexoUsuario>> obtenerTodosLosSexos() {
        List<SexoUsuario> sexos = tipoSexoService.leerTodasLasRegiones();
        return ResponseEntity.ok(sexos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SexoUsuario> obtenerSexoPorId(@PathVariable Integer id) {
        SexoUsuario sexo = tipoSexoService.leerRegionPorId(id);
        return ResponseEntity.ok(sexo);
    }

    @PostMapping
    public ResponseEntity<SexoUsuario> crearSexo(@RequestBody SexoTipoDTO sexoTipoDTO) {
        SexoUsuario nuevoSexo = tipoSexoService.crearRegion(sexoTipoDTO);
        return new ResponseEntity<>(nuevoSexo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SexoUsuario> actualizarSexo(@PathVariable Integer id, @RequestBody SexoTipoDTO sexoTipoDTO) {
        SexoUsuario sexoActualizado = tipoSexoService.actualizarRegion(id, sexoTipoDTO);
        return ResponseEntity.ok(sexoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSexo(@PathVariable Integer id) {
        tipoSexoService.eliminarRegion(id);
        return ResponseEntity.noContent().build();
    }
}
