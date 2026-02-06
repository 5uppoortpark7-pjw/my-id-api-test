package com.example.myip.dto;

public enum RequestType {
    LIVENESS_COMPARISON,
    LIVENESS_ONLY,
    COMPARISON_ONLY;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
