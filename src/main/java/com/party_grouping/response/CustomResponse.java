package com.party_grouping.response;

public class CustomResponse<T> {
    private int httpStatus = 200;
    private T data;

    public CustomResponse() {
    }

    public CustomResponse(T data) {
        this.data = data;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
