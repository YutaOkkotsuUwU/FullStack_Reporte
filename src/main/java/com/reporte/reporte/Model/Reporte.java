package com.reporte.reporte.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reporte {

   private int reporteId;
   private String rut_usuario;
   private String detalles;

}

