package com.oms.manage.exception;

public class OSMException extends RuntimeException{

    private int statusCode;
    private int httpStatusCode;
    private String errorMessage;

    public OSMException(int statusCode, int httpStatusCode, String errorMessage){
        this.statusCode = statusCode;
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
