package com.demo.app.dto;

public class FiltersDto {

    private String paramTitle;
    private ReportDto reportDto;

    /**
     * @return the paramTitle
     */
    public String getParamTitle() {
        return paramTitle;
    }

    /**
     * @param paramTitle the paramTitle to set
     */
    public void setParamTitle(String paramTitle) {
        this.paramTitle = paramTitle;
    }

    /**
     * @return the reportDto
     */
    public ReportDto getReportDto() {
        return reportDto;
    }

    /**
     * @param reportDto the reportDto to set
     */
    public void setReportDto(ReportDto reportDto) {
        this.reportDto = reportDto;
    }

}
