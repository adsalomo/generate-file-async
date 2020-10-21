package com.demo.app.enums;

public enum ReportTypeEnum {
    REPORT_DEMO("/jaspers/demo.jasper");
    
    private String uri;
    
    ReportTypeEnum(String uri) {
        this.uri = uri;
    }
    
    public String getUri() {
        return uri;
    }
}
