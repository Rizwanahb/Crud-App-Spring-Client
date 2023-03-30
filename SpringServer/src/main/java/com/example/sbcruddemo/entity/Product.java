package com.example.sbcruddemo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="product")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity will autoincrement integer values
    @Column(name="p_no")
    private Integer pNo;

    @Column(name="p_name",nullable = false,length = 100)
    private String pName;

    @Column(name="p_price",nullable = true)
    private float pPrice;
    @Column(name="p_type",nullable = false,length = 100)
    private String pType;

    public Integer getpNo() {
        return pNo;
    }

    public void setpNo(Integer pNo) {
        this.pNo = pNo;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public float getpPrice() {
        return pPrice;
    }

    public void setpPrice(float pPrice) {
        this.pPrice = pPrice;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pNo=" + pNo +
                ", pName='" + pName + '\'' +
                ", pPrice=" + pPrice +
                ", pType='" + pType + '\'' +
                '}';
    }
}

