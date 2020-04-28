package com.mindera.school.multiplayer.http.models;

public class APIError {
    private String error;
    private String msg;

    public APIError(String error, String msg) {
        this.error = error;
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }
}