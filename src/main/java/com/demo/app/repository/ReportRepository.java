package com.demo.app.repository;

import com.demo.app.model.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Integer>{
    
}
