package com.party_grouping.exception;

public class ApiException extends RuntimeException {
    private int status;
    private String code;
    private ErrorCode errorCode;
    private ApiException apiException;

    public ApiException() {
    }

    public ApiException(int status) {
        this.status = status;
    }

    public ApiException(String message, int status) {
        super(message);
        this.status = status;
    }

    public ApiException(String message, String code, int err_code) {
        super(message);
        this.status = err_code;
        this.code = code;
    }

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, ApiException apiException) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.errorCode = errorCode;
        this.apiException = apiException;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
