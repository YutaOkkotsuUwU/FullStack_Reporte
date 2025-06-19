package com.reporte.reporte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.reporte.reporte.Model.Reporte;
import com.reporte.reporte.Model.Entity.ReporteEntity;
import com.reporte.reporte.Repository.ReporteRepository;
import com.reporte.reporte.Service.ReporteService;


public class ReporteTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks 
    private ReporteService reporteService;

    private Reporte reporte;
    private ReporteEntity reporteEntity;

    @BeforeEach
    public void setup(){

        MockitoAnnotations.openMocks(this);
        reporte = new Reporte(1, "13.333.456-1", "Usuario creado");
        reporteEntity = new ReporteEntity();
        reporteEntity.setReporteId(1);
        reporteEntity.setRut_usuario("13.333.456-1");
        reporteEntity.setDetalles("Usuario creado");

    }

    @Test
    public void testAgregarReporte_nuevo(){

       when(reporteRepository.existsByReporteId(reporte.getReporteId())).thenReturn(false);
       when(reporteRepository.save(any(ReporteEntity.class))).thenReturn(reporteEntity);

       String resultado = reporteService.crearReporte(reporte);
       assertEquals("Usuario agregado exitosamente", resultado);

    }

    @Test
    public void testAgregarReporte_existente(){

        when(reporteRepository.existsByReporteId(reporte.getReporteId())).thenReturn(false);

        String resultado = reporteService.crearReporte(reporte);
        assertEquals("El usuario ya existe", resultado);

    }

    @Test
    public void testTraerReportePorId(){

        when(reporteRepository.findByReporteId(1)).thenReturn(reporteEntity);
        Reporte resultado = reporteService.obtenReporte(1);
        assertNotNull(resultado);
        assertEquals(1, resultado.getReporteId());

    }

    @Test 
    public void testTraerReporteNoExistente(){

        when(reporteRepository.findByReporteId(100)).thenReturn(null);
        Reporte resultado = reporteService.obtenReporte(100);
        assertNull(resultado);

    }

    @Test
    public void modificarReporte_existente(){

        when(reporteRepository.existsByReporteId(reporte.getReporteId())).thenReturn(true);
        when(reporteRepository.findByReporteId(reporte.getReporteId())).thenReturn(reporteEntity);
        when(reporteRepository.save(any(ReporteEntity.class))).thenReturn(reporteEntity);
        
        String result = reporteService.modificarReporte(reporte);
        assertEquals("Reporte modificado exitosamente", result);

    }

    @Test
    public void borrarReporte(){

        when(reporteRepository.existsById(1)).thenReturn(true);
        doNothing().when(reporteRepository).deleteByReporteId(1);
        
        String resultado = reporteService.eliminarReporte(1);
        
        assertEquals("Reporte eliminado exitosamente", resultado);

    }

}
