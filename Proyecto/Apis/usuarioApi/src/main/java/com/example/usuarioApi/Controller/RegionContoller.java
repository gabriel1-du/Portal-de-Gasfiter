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

import com.example.usuarioApi.DTO.clasesRegionDTO.leerRegionDTO;
import com.example.usuarioApi.DTO.clasesRegionDTO.RegionDTO;
import com.example.usuarioApi.Model.Region;
import com.example.usuarioApi.Service.RegionService;

@RestController
@RequestMapping("api/regionesApi")
public class RegionContoller {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<leerRegionDTO>> obtenerTodasLasRegiones() {
        List<leerRegionDTO> regiones = regionService.leerTodasLasRegiones();
        return ResponseEntity.ok(regiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<leerRegionDTO> obtenerRegionPorId(@PathVariable Integer id) {
        leerRegionDTO region = regionService.leerRegionPorId(id);
        return ResponseEntity.ok(region);
    }

    @PostMapping
    public ResponseEntity<Region> crearRegion(@RequestBody RegionDTO regionDTO) {
        Region nuevaRegion = regionService.crearRegion(regionDTO);
        return new ResponseEntity<>(nuevaRegion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> actualizarRegion(@PathVariable Integer id, @RequestBody RegionDTO regionDTO) {
        Region regionActualizada = regionService.actualizarRegion(id, regionDTO);
        return ResponseEntity.ok(regionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegion(@PathVariable Integer id) {
        regionService.eliminarRegion(id);
        return ResponseEntity.noContent().build();
    }
}
