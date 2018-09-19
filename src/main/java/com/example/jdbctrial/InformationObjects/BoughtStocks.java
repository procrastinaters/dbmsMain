package com.example.jdbctrial.InformationObjects;

public class BoughtStocks {

    String username;
    String stock;
    Integer quantity;
    Float pricePerShare;
    Float price;

    public BoughtStocks(){}

    public BoughtStocks(String username, String stock, Integer quantity, Float pricePerShare, Float price) {
        this.username = username;
        this.stock = stock;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.price = price;
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
}
