package com.oms.manage.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalApplicationExceptionHandler {

    @ExceptionHandler(value = {OSMException.class})
    public ResponseEntity<ErrorMessage> handleOSMException(OSMException exception){
    ErrorMessage message = new ErrorMessage(exception.getStatusCode(), exception.getHttpStatusCode(), exception.getErrorMessage());
    return new ResponseEntity<ErrorMessage>(message,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class })
    public ResponseEntity<GenericResponse> handleException(Exception exception) {
        GenericResponse er = new GenericResponse(exception.getMessage());
        return new ResponseEntity<GlobalApplicationExceptionHandler.GenericResponse>(er, new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    private class ErrorMessage{
        private int statusCode;
        private int httpStatusCode;
        private String errorMessage;

        public ErrorMessage(int statusCode, int httpStatusCode, String errorMessage) {
            this.statusCode = statusCode;
            this.httpStatusCode = httpStatusCode;
            this.errorMessage = errorMessage;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public int getHttpStatusCode() {
            return httpStatusCode;
        }

        public void setHttpStatusCode(int httpStatusCode) {
            this.httpStatusCode = httpStatusCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    private class GenericResponse{
        String messgae;



        public GenericResponse(String messgae) {
            this.messgae = messgae;
        }

        public String getMessgae() {
            return messgae;
        }

        public void setMessgae(String messgae) {
            this.messgae = messgae;
        }

    }
}
