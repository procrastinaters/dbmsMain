package com.example.jdbctrial.InformationObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SoldStocks {

    String transId;
    String symbol;
    String username;
    Integer quantity;
    Date date;
    Float sellPrice;
    Double profitLoss;

    public SoldStocks(){

        profitLoss=0.0;
    }

    public SoldStocks(String transId, String symbol, String username, Integer quantity, Date date, Float sellPrice, Double profitLoss) {
        this.transId = transId;
        this.symbol = symbol;
        this.username = username;
        this.quantity = quantity;
        this.date = date;
        this.sellPrice = sellPrice;
        this.profitLoss = profitLoss;
    }

    public String getTransId() {
        return transId;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getUsername() {
        return username;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public Double getProfitLoss() {
        return profitLoss;
    }
}
