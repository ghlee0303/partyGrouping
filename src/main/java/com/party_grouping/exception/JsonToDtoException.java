package com.party_grouping.exception;

public class JsonToDtoException extends RuntimeException {
    protected Integer err_code;

    public JsonToDtoException() {
    }

    public JsonToDtoException(String message, Integer err_code) {
        super(message);
        this.err_code = err_code;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }
}
