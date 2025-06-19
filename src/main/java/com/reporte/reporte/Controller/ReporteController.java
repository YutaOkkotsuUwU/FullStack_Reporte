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
import com.reporte.reporte.Model.Dto.UsuarioDto;
import com.reporte.reporte.Model.Dto.UsuarioReporteDto;
import com.reporte.reporte.Service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ReporteController {
    
    @Autowired
    private ReporteService reporteService;

    //Obtener todos los reportes

    @Operation(summary = "Obtener todos los reportes")

    @GetMapping("/Reportes")
    public ResponseEntity<List<Reporte>> mostrarReportes() {

        List<Reporte> reportes = reporteService.ObtenerTodosLosReportes();

        if(reportes == null || reportes.isEmpty()){;
            
           return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(reportes);

    }

    //Crear un nuevo reporte

    @Operation(summary = "Crear un nuevo reporte")

    @PostMapping("/Reportes")
    public ResponseEntity<String> crearReporte(@RequestBody Reporte reporte)
    {
        String respuesta = reporteService.crearReporte(reporte);

        if(respuesta != null) {

            return ResponseEntity.ok(respuesta);
        
        }
        
        return ResponseEntity.notFound().build();

    }

    //Eliminar un reporte por ID

    @Operation(summary = "Eliminar un reporte por ID")

    @DeleteMapping("/Reportes/{reporteId}")
    public ResponseEntity<String> eliminarReporte(@PathVariable int reporteId)
    { 
         
        String respuesta = reporteService.eliminarReporte(reporteId);

        if(respuesta == null) {

            return ResponseEntity.notFound().build();
        
        }
        return ResponseEntity.ok(respuesta);
    
    }

    //Obtener un reporte por ID

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

    //Actualizar un reporte por ID

    @Operation(summary = "Actualizar un reporte por ID")
    
    @PutMapping("/Reportes")
    public ResponseEntity<String> modificarReporte(@RequestBody Reporte reporte)
    {
       
        if (reporteService.modificarReporte(reporte) != null )  {

            return ResponseEntity.ok(reporteService.modificarReporte(reporte));
        
        }
        return ResponseEntity.notFound().build();

    }

    //Obtener todos los usuarios desde el microservicio de usuarios

    @Operation(summary = "Obtener todos los usuarios desde el microservicio de usuarios")
    
    @GetMapping("/usuarios")
    
    public ResponseEntity<List<UsuarioDto>> obtenerTodosLosUsuarios() {
        
        List<UsuarioDto> usuarios = reporteService.obtenerTodosLosUsuarios();
        
        if (usuarios == null || usuarios.isEmpty()) {
            
            return ResponseEntity.noContent().build();
        
        }
        return ResponseEntity.ok(usuarios);
    }

    //Obtener usuario y reporte por reporteId

    @Operation(summary = "Obtener usuario y reporte por reporteId")
    
    @GetMapping("/usuario-reporte/{reporteId}")
    
    public ResponseEntity<UsuarioReporteDto> obtenerUsuarioYReportePorReporteId(@PathVariable int reporteId) {
        
        UsuarioReporteDto dto = reporteService.obtenerUsuarioYReportePorReporteId(reporteId);
        
        if (dto == null) {
            
            return ResponseEntity.notFound().build();
        
        }
        return ResponseEntity.ok(dto);
    }

    

}