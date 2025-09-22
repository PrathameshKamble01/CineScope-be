package com.cinescopefinal.CineScope.dto;

import com.cinescopefinal.CineScope.entities.enums.Status;

public class StatusUpdateRequest {

    private Status status;

    public StatusUpdateRequest(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
