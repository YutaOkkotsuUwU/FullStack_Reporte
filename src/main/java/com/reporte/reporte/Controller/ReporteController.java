package com.reporte.reporte.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reporte.reporte.Model.Reporte;
import com.reporte.reporte.Model.Dto.ReporteDto;
import com.reporte.reporte.Service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ReporteController {
    
    @Autowired
    private ReporteService reporteService;

    @Operation(summary = "Obtener todos los reportes")

    @GetMapping("/Reportes")
    public ResponseEntity<List<Reporte>> mostrarReportes() {
        if(reporteService.ObtenerTodosLosReportes() != null){;
            
            return ResponseEntity.ok(reporteService.ObtenerTodosLosReportes());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo reporte")

    @PostMapping("/Reportes")
    public ResponseEntity<String> crearReporte(@RequestBody Reporte reporte)
    {
        
        return ResponseEntity.ok(reporteService.crearReporte(reporte));

    }

    @Operation(summary = "Eliminar un reporte por ID")

    @DeleteMapping("/Reportes/{reporteId}")
    public ResponseEntity<String> eliminarReporte(@PathVariable int reporteId)
    { 
         
        return ResponseEntity.ok(reporteService.eliminarReporte(reporteId));
    
    }

    @Operation(summary = "Obtener un reporte por ID")

    @GetMapping("/Reportes/{reporteId}")
    public ResponseEntity<Reporte> obtenerReporte(@PathVariable int reporteId)
    {
        Reporte reporte = reporteService.obtenReporte(reporteId);
        if (reporte != null) {

            return ResponseEntity.ok(reporte);

        }
        return ResponseEntity.notFound().build();
        
    }

    @Operation(summary = "Actualizar un reporte por ID")
    
    @PutMapping("/Reportes")
    public ResponseEntity<String> modificarReporte(@RequestBody Reporte reporte)
    {
       
        if (reporteService.modificarReporte(reporte) != null) {

            return ResponseEntity.ok(reporteService.modificarReporte(reporte));
        
        }
        return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Obtener un reporteDto por ID")
    
    @GetMapping("/obtenerReportes/{reporteId}")
    public ResponseEntity<ReporteDto> obtenerReporteDto(@PathVariable int reporteId)
    {
        if(reporteService.obtenerReporteDto(reporteId) != null){
            
            return ResponseEntity.ok(reporteService.obtenerReporteDto(reporteId));

        }
        return ResponseEntity.notFound().build();
        
    }



}