package com.demo.app.controller;

import com.demo.app.dto.FiltersDto;
import com.demo.app.dto.ReportDto;
import com.demo.app.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportServiceImpl;

    @PostMapping
    public ReportDto generateReport(@RequestBody FiltersDto filtersDto) {
        return this.reportServiceImpl.generateReport(filtersDto);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource>
            getReport(@RequestParam(name = "id", required = true) int id) throws Exception {
        InputStreamResource resource = new InputStreamResource(this.reportServiceImpl.getReport(id));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }
}
