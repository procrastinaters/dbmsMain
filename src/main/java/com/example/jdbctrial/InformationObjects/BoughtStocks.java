package com.example.jdbctrial.InformationObjects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoughtStocks {

    String username;
    String stock;
    Integer quantity;
    Float pricePerShare;
    Float price;
    Integer transId;
    Integer sold;



    public BoughtStocks(){}

    public BoughtStocks(String username, String stock, Integer quantity, Float pricePerShare, Float price, Integer transId, Integer sold) {
        this.username = username;
        this.stock = stock;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.price = price;
        this.transId=transId;
        this.sold=sold;
    }

    public String getUsername() {
        return username;
    }

    public String getStock() {
        return stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getPricePerShare() {
        return pricePerShare;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getTransId() {
        return transId;
    }

    public Integer getSold() {
        return sold;
    }
}
