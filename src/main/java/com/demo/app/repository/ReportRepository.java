package com.demo.app.repository;

import com.demo.app.model.Report;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Integer>{
    
    @Query("SELECT r FROM Report r")
    List<Report> findAllReports();
    
}
