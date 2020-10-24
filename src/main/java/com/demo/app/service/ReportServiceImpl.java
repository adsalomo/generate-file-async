package com.demo.app.service;

import com.demo.app.dto.FiltersDto;
import com.demo.app.dto.ReportDto;
import com.demo.app.enums.StatusEnum;
import com.demo.app.model.Report;
import com.demo.app.repository.ReportRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private AsyncUtilityService asyncUtilityService;

    public ReportDto generateReport(FiltersDto filtersDto) {
        Report report = new Report();
        report.setCreatedAt(LocalDateTime.now());
        report.setStatus(StatusEnum.IN_PROGRESS.getValue());
        report = this.reportRepository.save(report);

        // CALL METHOD ASYNC
        this.asyncUtilityService.generateReport(filtersDto, report);

        return new ReportDto(report.getId(), report.getStatus());
    }

    public InputStream getReport(int id) throws Exception {
        Optional<Report> report = this.reportRepository.findById(id);
        if (!report.isPresent()) {
            throw new Exception("No existe informacion");
        } else {
            StatusEnum statusEnum = StatusEnum.get(report.get().getStatus());
            switch (statusEnum) {
                case IN_PROGRESS:
                    throw new Exception("El reporte esta en progreso");
                case ERROR:
                    throw new Exception("El reporte se completo con errores");
                case COMPLETED:
                    File file = new File(report.get().getReportPath());
                    if (!file.exists()) {
                        throw new Exception("No existe informacion");
                    }
                    return new FileInputStream(file);
                default:
                    throw new Exception("No existe informacion");
            }
        }
    }
    
    public List<Report> getAll() {
        List<Report> reports = new ArrayList<>();
        this.reportRepository.findAll().forEach(reports::add);
        return reports;
    }
}
