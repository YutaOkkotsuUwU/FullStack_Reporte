package com.reporte.reporte.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reporte.reporte.Model.Reporte;
import com.reporte.reporte.Model.Dto.ReporteDto;
import com.reporte.reporte.Model.Entity.ReporteEntity;
import com.reporte.reporte.Repository.ReporteRepository;




@Service
public class ReporteService {
    
    @Autowired
    private ReporteRepository reporteRepository;

    //private List<Reporte> reportes = new ArrayList<>();
    
    public List<Reporte> ObtenerTodosLosReportes() {

        try{

            List<ReporteEntity> listaReporte = (List<ReporteEntity>) reporteRepository.findAll();
            
            if(listaReporte.isEmpty()){
                
                throw new IllegalArgumentException("No reportes encontrados");

            }

            
            List<Reporte> reportes = new ArrayList<>();
            
            for (ReporteEntity reporte : listaReporte) {

                Reporte nuevoReporte = new Reporte();
                
                nuevoReporte.setReporteId(reporte.getReporteId());
                nuevoReporte.setDetalles(reporte.getDetalles());

                reportes.add(nuevoReporte);

            }
            return reportes;

        }
        catch (IllegalArgumentException e) {
            
            System.out.println("Error de validación: " + e.getMessage());
            return new ArrayList<>();
        
        }
        catch (Exception e) {
            
            System.out.println("Error al obtener los soportes: " + e.getMessage());
            return new ArrayList<>();

        }


    }

    public Reporte obtenReporte(int reporteId) {
        
        try{

            if (reporteId <= 0) {
                
                throw new IllegalArgumentException("El ID del curso no puede ser menor o igual a cero");
            }

            ReporteEntity reporte = reporteRepository.findByReporteId(reporteId);
            
            if(reporte == null){
                
                throw new IllegalArgumentException("El reporte no existe");

            }

            Reporte mostrarReporte = new Reporte();
            
            mostrarReporte.setReporteId(reporte.getReporteId());
            mostrarReporte.setDetalles(reporte.getDetalles());

            return mostrarReporte;

        }
        catch (IllegalArgumentException e) {
            
            System.out.println("Error de validación: " + e.getMessage());
            return null;
        
        }
        catch (Exception e) {
            
            System.out.println("Error al obtener el soporte: " + e.getMessage());
            return null;

        }

    }

    public String crearReporte(Reporte reporte) {
        
        try{

            Boolean estado = reporteRepository.existsByReporteId(reporte.getReporteId());
            if(estado != true){
                
                ReporteEntity nuevoReporte = new ReporteEntity();
                
                nuevoReporte.setDetalles(reporte.getDetalles());
                nuevoReporte.setReporteId(reporte.getReporteId());

                reporteRepository.save(nuevoReporte);
                
                return "Soporte creado correctamente";
            }
            else{
                
                return "El soporte ya existe";

            }

        }
        catch (Exception e) {
            
            System.out.println("Error al crear el soporte: " + e.getMessage());
            return "Error al crear el soporte";

        }
    }

    @Transactional
    public String eliminarReporte(int reporteId) {
        
        try{
            
            if(reporteRepository.existsByReporteId(reporteId)){
                
                reporteRepository.deleteByReporteId(reporteId);
                
                return "Soporte eliminado correctamente";
            }
            else{
                
                System.out.println("El soporte no existe");
                return "El soporte no existe";

            }

        }
        catch (Exception e) {
            
            System.out.println("Error al eliminar el soporte: " + e.getMessage());
            return "Error al eliminar el soporte";

        }
    }

    public String  modificarReporte (Reporte reporte){
        
        try{
            
            if(reporteRepository.existsByReporteId(reporte.getReporteId())){
                
                ReporteEntity reporteExistente = reporteRepository.findByReporteId(reporte.getReporteId());

                if (reporteExistente != null) {
               
                reporteExistente.setReporteId(reporte.getReporteId());
                reporteExistente.setDetalles(reporte.getDetalles());

                reporteRepository.save(reporteExistente);
                
                }
                return "Soporte modificado correctamente";
                
            }
            else{
                
                return "El soporte no existe";

            }

        }
        catch (Exception e) {
            
            System.out.println("Error al modificar el soporte: " + e.getMessage());
            return "Error al modificar el soporte";

        }
    }

    public ReporteDto obtenerReporteDto(int reporteId) {
        try {

            if(reporteRepository.existsByReporteId(reporteId) != null){
                
                ReporteEntity nuevoReporte = reporteRepository.findByReporteId(reporteId);
                ReporteDto responseReporte = new ReporteDto(nuevoReporte.getReporteId(), nuevoReporte.getDetalles());

                return responseReporte;
            }
            else{
                
                return null;

            }
            
        } catch (Exception e) {

            System.out.println("Error al obtener el soporte: " + e.getMessage());
            return null;
        }
    
    }
 
}
