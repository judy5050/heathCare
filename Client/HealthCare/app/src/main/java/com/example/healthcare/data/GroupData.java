package com.example.healthcare.data;

public class GroupData {
    private boolean inSuccess;
    private int code;
    private String message;

    public boolean isInSuccess() {
        return inSuccess;
    }

    public void setInSuccess(boolean inSuccess) {
        this.inSuccess = inSuccess;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
