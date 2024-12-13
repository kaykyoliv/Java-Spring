package com.devsuperior.crud.dto;

import java.time.Instant;

public class CustomErrorDTO {

    public Instant timestamp;
    public Integer status;
    public String error;
    public String path;

    public CustomErrorDTO(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
