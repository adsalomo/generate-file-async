package com.demo.app.dto;

public class ReportDto {

    private final int reportId;
    private final String status;
    
    public ReportDto(int reportId, String status) {
        this.reportId = reportId;
        this.status = status;
    }

    /**
     * @return the reportId
     */
    public int getReportId() {
        return reportId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

}
