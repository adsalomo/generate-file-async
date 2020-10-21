package com.demo.app.service;

import com.demo.app.dto.FiltersDto;
import com.demo.app.enums.ReportTypeEnum;
import com.demo.app.enums.StatusEnum;
import com.demo.app.model.Report;
import com.demo.app.repository.ReportRepository;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AsyncUtilityService {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncUtilityService.class);
    private static final String BASE_PATH = System.getProperty("java.io.tmpdir").concat("\\");

    @Autowired
    private ReportRepository reportRepository;

    @Async
    public void generateReport(FiltersDto filtersDto, Report report) {
        try {
            InputStream jasper = AsyncUtilityService.class
                    .getResourceAsStream(ReportTypeEnum.REPORT_DEMO.getUri());
            
            Map<String, Object> params = new HashMap<>();
            params.put("PARAM_TITLE", filtersDto.getParamTitle());
            
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(jasper, params, new JREmptyDataSource());

            String path = BASE_PATH.concat(String.valueOf(Calendar.getInstance()
                            .getTimeInMillis())).concat(".pdf");
            
            JasperExportManager.exportReportToPdfFile(jasperPrint, path);
            
            // SUCCESFULLY
            report.setReportPath(path);
            report.setModifiedAt(LocalDateTime.now());
            report.setStatus(StatusEnum.COMPLETED.getValue());
            
            this.reportRepository.save(report);
        } catch (JRException ex) {
            // ERROR
            report.setStatus(StatusEnum.ERROR.getValue());
            LOG.error("Error", ex);
        }
        
        // CHANGE STATUS
        this.reportRepository.save(report);
    }
}
