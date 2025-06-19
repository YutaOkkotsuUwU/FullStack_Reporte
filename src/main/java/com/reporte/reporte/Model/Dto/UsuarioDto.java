package com.reporte.reporte.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private String rut;
    private String nombre;
    private String apellido_paterno;
    private String correo;
    private int telefono;

    
}