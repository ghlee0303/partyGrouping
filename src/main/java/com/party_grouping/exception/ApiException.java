package com.party_grouping.exception;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ApiException extends RuntimeException {
    protected Integer err_code;
    protected ResponseEntity<String> response;
    protected String test;

    public ApiException() {
    }

    public ApiException(String message, Integer err_code, String test) {
        super(message);
        this.err_code = err_code;
        this.test = test;

    }
    public ApiException(String message, Integer err_code) {
        super(message);
        this.err_code = err_code;
    }

    public ApiException(String message, Integer err_code, ResponseEntity<String> response) {
        super(message);
        this.err_code = err_code;
        this.response = response;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }

    public Map<String, Object> responseMapping() {
        return response == null ? null : new JSONObject(response.getBody()).toMap();
    }
}
