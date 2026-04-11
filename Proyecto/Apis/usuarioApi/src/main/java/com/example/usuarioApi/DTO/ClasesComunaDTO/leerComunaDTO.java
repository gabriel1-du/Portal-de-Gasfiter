package com.example.usuarioApi.DTO.ClasesComunaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class leerComunaDTO {

    private Integer idComuna;
    private String nombreComuna;
    private Integer idRegion;
    private String nombreRegion;
}
