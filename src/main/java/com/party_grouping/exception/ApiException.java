package com.party_grouping.exception;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ApiException extends RuntimeException {
    protected Integer err_code;

    public ApiException() {
    }

    public ApiException(String message, Integer err_code) {
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
