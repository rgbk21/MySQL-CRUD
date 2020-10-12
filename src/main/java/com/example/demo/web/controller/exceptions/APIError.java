package com.example.demo.web.controller.exceptions;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class APIError {

    private int status;
    private String message;
    private String devMessage;

    public APIError(int status, String message, String devMessage) {
        this.status = status;
        this.message = message;
        this.devMessage = devMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("message", message)
                .add("devMessage", devMessage)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APIError apiError = (APIError) o;
        return status == apiError.status &&
                Objects.equal(message, apiError.message) &&
                Objects.equal(devMessage, apiError.devMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, message, devMessage);
    }
}
