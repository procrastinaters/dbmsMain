package com.example.jdbctrial.InformationObjects;

public class Admin {

    private String username;
    private Integer boughtNo;
    private Integer soldNo;
    private Float balance;

    public Admin(String username, Integer boughtNo, Integer soldNo, Float balance) {
        this.username = username;
        this.boughtNo = boughtNo;
        this.soldNo = soldNo;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public Integer getBoughtNo() {
        return boughtNo;
    }

    public Integer getSoldNo() {
        return soldNo;
    }

    public Float getBalance() {
        return balance;
    }
}
