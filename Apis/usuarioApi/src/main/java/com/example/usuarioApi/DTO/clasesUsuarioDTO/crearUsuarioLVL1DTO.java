package com.example.usuarioApi.DTO.clasesUsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class crearUsuarioLVL1DTO {
    
    // Datos personales
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Integer idSexoUsu;
    private String foto; 
    // Credenciales
    private String correoElec;
    private String password;
    
    // Identificación y Contacto
    private String numeroTelef;
    private Integer idTipoUsu; 
    
    // Ubicación y Especialidad (Opcionales dependiendo del tipo de usuario)
    private Integer idRegionUsu;
    private Integer idComunaUsu;
 

}
