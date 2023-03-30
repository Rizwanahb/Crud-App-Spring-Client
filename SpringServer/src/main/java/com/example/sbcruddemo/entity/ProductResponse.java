package com.example.sbcruddemo.entity;

public class ProductResponse {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductResponse(String status) {
        this.status = status;
    }

    public ProductResponse() {
    }
}
