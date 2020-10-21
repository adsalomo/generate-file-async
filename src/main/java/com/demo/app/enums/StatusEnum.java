package com.demo.app.enums;

public enum StatusEnum {
    IN_PROGRESS("InProgress"), ERROR("Error"), COMPLETED("Completed");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public static StatusEnum get(String value) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
