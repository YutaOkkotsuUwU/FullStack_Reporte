package com.reporte.reporte.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reporte.reporte.Model.Entity.ReporteEntity;




@Repository

public interface ReporteRepository extends JpaRepository<ReporteEntity, Integer> {

    ReporteEntity findByReporteId(int reporteId);
    Boolean existsByReporteId(int reporteId);
    void deleteByReporteId(int reporteId);

}