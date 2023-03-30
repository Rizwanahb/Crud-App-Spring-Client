package com.example.springclient.model;


import java.io.Serializable;

public class Product implements Serializable {


    public int pNo;
    public String pName;
    public float pPrice;
    public String pType;

    public int getpNo() {
        return pNo;
    }

    public void setpNo(int pNo) {
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
    public Product(int pNo, String pName, String pType, int productType,float pPrice)
        {
        super();
        this.pNo = pNo;
        this.pName = pName;
        this.pType = pType;
        //this.pType = productType;
        this.pPrice = pPrice;
    }

   public Product() {
       // TODO Auto-generated constructor stub
   }    }




