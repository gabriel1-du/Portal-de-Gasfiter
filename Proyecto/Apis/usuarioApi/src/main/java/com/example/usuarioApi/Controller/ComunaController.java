package com.example.usuarioApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarioApi.DTO.ClasesComunaDTO.ActualizarNombreComunaDTO;
import com.example.usuarioApi.DTO.ClasesComunaDTO.ComunaDTO;
import com.example.usuarioApi.DTO.ClasesComunaDTO.leerComunaDTO;
import com.example.usuarioApi.Model.Comuna;
import com.example.usuarioApi.Service.ComunaService;

@RestController
@RequestMapping("api/comunasApi")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<leerComunaDTO>> obtenerTodasLasComunas() {
        return ResponseEntity.ok(comunaService.leerTodasLasComunas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<leerComunaDTO> obtenerComunaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(comunaService.leerComunaPorId(id));
    }

    @PostMapping
    public ResponseEntity<Comuna> crearComuna(@RequestBody ComunaDTO comunaDTO) {
        return new ResponseEntity<>(comunaService.crearComuna(comunaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> actualizarComuna(@PathVariable Integer id, @RequestBody ComunaDTO comunaDTO) {
        return ResponseEntity.ok(comunaService.actualizarComuna(id, comunaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComuna(@PathVariable Integer id) {
        comunaService.eliminarComuna(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> actualizarNombreComuna(@PathVariable Integer id, @RequestBody ActualizarNombreComunaDTO comunaDTO) {
        return ResponseEntity.ok(comunaService.actualizarNombreComuna(id, comunaDTO));
    }
}
