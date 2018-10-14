package com.example.jdbctrial.InformationObjects;

import java.sql.Date;
import java.sql.Timestamp;

public class TransactionHistory {

    private String transid;
    private String symbol;
    private Integer quantity;
    private Double price;
    private Double pl;
    Date timestamp;


    public TransactionHistory(String transid, String symbol, Integer quantity, Double price, Double pl, Date timestamp) {
        this.transid = transid;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.pl = pl;
        this.timestamp=timestamp;
    }

    public String getTransid() {
        return transid;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getPl() {
        return pl;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
