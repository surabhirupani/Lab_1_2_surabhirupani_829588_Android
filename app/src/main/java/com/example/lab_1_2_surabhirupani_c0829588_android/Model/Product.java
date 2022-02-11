package com.example.lab_1_2_surabhirupani_c0829588_android.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product")
public class Product implements Serializable {
    @PrimaryKey
    private Long productId;

    @ColumnInfo
    private String productName;

    @ColumnInfo
    private String productDesc;

    @ColumnInfo
    private Double productPrice;

    @ColumnInfo
    private Double productLat;

    @ColumnInfo
    private Double productLong;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductLat() {
        return productLat;
    }

    public void setProductLat(Double productLat) {
        this.productLat = productLat;
    }

    public Double getProductLong() {
        return productLong;
    }

    public void setProductLong(Double productLong) {
        this.productLong = productLong;
    }
}
