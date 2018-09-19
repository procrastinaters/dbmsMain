package com.example.jdbctrial.InformationObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    String username;
    Integer balance;
    String password;

    public Account(){}

    public Account(String username,
                   Integer balance,
                   String password) {
        this.username = username;
        this.balance = balance;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

}
