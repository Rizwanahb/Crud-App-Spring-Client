package com.example.springclient.model;

public class ProductResponse {
    public String status;

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
