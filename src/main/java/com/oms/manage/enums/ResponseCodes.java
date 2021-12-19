package com.oms.manage.enums;

public enum ResponseCodes {
    ORDER_NOT_FOUND(12001,400,"Order not found in database");

    private int statusCode;
    private int httpStatusCode;
    private String message;

    ResponseCodes(int statusCode, int httpStatusCode, String message) {
        this.statusCode = statusCode;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getMessage() {
        return message;
    }
}
