package com.example.usuarioApi.Controller;

import com.example.usuarioApi.DTO.ClasesTipoUsuariosDTO.crearTipoUsuarioDTO;
import com.example.usuarioApi.DTO.ClasesTipoUsuariosDTO.leerTipoUsuarioDTO;
import com.example.usuarioApi.Model.TipoUsuario;
import com.example.usuarioApi.Service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tiposUsuarioApi")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<leerTipoUsuarioDTO>> obtenerTodosLosTiposUsuario() {
        return ResponseEntity.ok(tipoUsuarioService.leerTodosLosTiposUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<leerTipoUsuarioDTO> obtenerTipoUsuarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoUsuarioService.leerTipoUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> crearTipoUsuario(@RequestBody crearTipoUsuarioDTO tipoUsuarioDTO) {
        return new ResponseEntity<>(tipoUsuarioService.crearTipoUsuario(tipoUsuarioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> actualizarTipoUsuario(@PathVariable Integer id, @RequestBody crearTipoUsuarioDTO tipoUsuarioDTO) {
        return ResponseEntity.ok(tipoUsuarioService.actualizarTipoUsuario(id, tipoUsuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoUsuario(@PathVariable Integer id) {
        tipoUsuarioService.eliminarTipoUsuario(id);
        return ResponseEntity.noContent().build();
    }
}